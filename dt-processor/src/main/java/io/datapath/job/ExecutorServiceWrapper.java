package io.datapath.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceWrapper {

	private ExecutorService executorService;

	public ExecutorServiceWrapper() {
		
	}

	public ExecutorServiceWrapper(int size) {
		createNewExecutorService(size);
	}

	public ExecutorService get() {
		return executorService;
	}

	<T> void submit(Runnable task) {
		executorService.submit(task);
	}

	public void shutdown() {
		executorService.shutdown();
	}

	public void createNewExecutorService(int size) {
		executorService = Executors.newWorkStealingPool(size);
	}

}
