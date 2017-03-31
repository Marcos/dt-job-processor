package io.datapath.job;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ClassWrapperTest {

	private ClassWrapper classWrapper;

	@Test
	public void newInstance() throws Exception {
		classWrapper = new ClassWrapper(FakeJob.class);
		Job job = classWrapper.newInstance();
		assertThat(job, instanceOf(FakeJob.class));
	}
	
	

	
}
