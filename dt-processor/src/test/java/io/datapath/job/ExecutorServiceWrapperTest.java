package io.datapath.job;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.concurrent.ExecutorService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ExecutorServiceWrapperTest {

	private static final Runnable TASK = () -> System.out.println("teste");

	@Mock
	private ExecutorService executorService;

	@InjectMocks
	private ExecutorServiceWrapper executorServiceWrapper;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void createNewExecutorService() {
		executorServiceWrapper.createNewExecutorService(1);
		assertThat(executorServiceWrapper.get(), notNullValue());
	}

	@Test
	public void submit() {
		executorServiceWrapper.submit(TASK);
		verify(executorService).submit(TASK);
	}

	@Test
	public void shutdown() {
		executorServiceWrapper.shutdown();
		verify(executorService).shutdown();
	}

}
