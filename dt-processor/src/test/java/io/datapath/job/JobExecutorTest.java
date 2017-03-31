package io.datapath.job;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import io.datapath.job.exceptions.JobNotFoundException;

public class JobExecutorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private Map<String, ClassWrapper> jobs;

	@Mock
	private ExecutorServiceWrapper executor;

	@Mock
	private ConcurrentLinkedQueue<Job> queue;

	@Mock
	private ReflectionsWrapper reflectionsWrapper;

	@Mock
	private ClassWrapper jobClassMock;

	@Mock
	private Job jobMock;

	@Mock
	private Iterator<Job> iteratorMock;

	private JobExecutor jobExecutor;

	public JobExecutorTest() {
		initMocks(this);
		when(reflectionsWrapper.scan()).thenReturn(jobs);
		jobExecutor = new JobExecutor(false, executor, queue, reflectionsWrapper);
	}

	@Test
	public void addToQueue() throws Exception {
		when(jobs.get("jobName")).thenReturn(jobClassMock);
		when(jobClassMock.newInstance()).thenReturn(jobMock);
		jobExecutor.addToQueue("jobName");
		verify(queue).add(jobMock);
	}

	@Test
	public void addToQueueWhenJobIsNotFound() throws Exception {
		when(jobs.get("jobName")).thenReturn(null);
		when(jobClassMock.newInstance()).thenReturn(jobMock);
		thrown.expect(JobNotFoundException.class);
		jobExecutor.addToQueue("jobName");
	}

	@Test
	public void setWorkersSize() throws Exception {
		jobExecutor.setWorkersSize(10);
		verify(executor).shutdown();
		verify(executor).createNewExecutorService(10);
	}

	@Test
	public void run() throws Exception {
		when(queue.iterator()).thenReturn(iteratorMock);
		when(iteratorMock.hasNext()).thenReturn(true).thenReturn(false);
		when(iteratorMock.next()).thenReturn(jobMock);
		jobExecutor.run();
		verify(executor).submit(org.mockito.Matchers.any(Runnable.class));
		verify(iteratorMock).remove();
	}

	@Test
	public void runWhenJobIsNull() throws Exception {
		when(queue.iterator()).thenReturn(iteratorMock);
		when(iteratorMock.hasNext()).thenReturn(true).thenReturn(false);
		when(iteratorMock.next()).thenReturn(null);
		doThrow(NullPointerException.class).when(executor).submit(org.mockito.Matchers.any(Runnable.class));
		jobExecutor.run();
		verify(executor, never()).submit(org.mockito.Matchers.any(Runnable.class));
		verify(iteratorMock).remove();
	}

	@Test
	public void execute() throws Exception {
		jobExecutor.execute(jobMock);
		verify(jobMock).execute();
	}

	@Test
	public void executeWhenThrowsException() throws Exception {
		doThrow(NullPointerException.class).when(jobMock).execute();
		jobExecutor.execute(jobMock);
		verify(jobMock).execute();
	}

}
