package io.datapath.job;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import io.datapath.job.exceptions.JobNotFoundException;

@Scope(value = "singleton")
@Service
public class JobExecutor {

	private ReflectionsWrapper reflectionsWrapper;

	private Map<String, ClassWrapper> jobs = new HashMap<>();

	private ExecutorServiceWrapper executor = new ExecutorServiceWrapper(5);

	private BlockingQueue<Job> queue = new LinkedBlockingQueue<>();

	public JobExecutor(boolean start, ExecutorServiceWrapper executor, BlockingQueue<Job> queue,
			ReflectionsWrapper reflectionsWrapper) {
		this.executor = executor;
		this.queue = queue;
		this.reflectionsWrapper = reflectionsWrapper;
		initialize(start);
	}

	public JobExecutor() {
		reflectionsWrapper = new ReflectionsWrapper();
		initialize(true);
	}

	private void initialize(boolean start) {
		jobs = reflectionsWrapper.scan();
		if (start)
			start();
	}

	public void addToQueue(String jobExecutor) {
		try {
			ClassWrapper jobClass = jobs.get(jobExecutor);
			Job instance = jobClass.newInstance();
			queue.put(instance);
		} catch (Exception e) {
			throw new JobNotFoundException(e);
		}
	}

	public void setWorkersSize(int size) {
		executor.shutdown();
		executor.createNewExecutorService(size);
	}

	protected void run()  {
		try {
			runJob(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void start() {
		new Thread(() -> {
			while (true)
					run();
		}).start();
	}

	private void runJob(Job job) {
		if (job != null)
			executor.submit(() -> execute(job));
	}

	protected void execute(Job job) {
		try {
			job.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
