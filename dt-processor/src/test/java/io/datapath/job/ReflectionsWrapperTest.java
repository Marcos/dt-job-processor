package io.datapath.job;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class ReflectionsWrapperTest {

	@InjectMocks
	private ReflectionsWrapper reflectionsWrapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void scan() throws Exception {
		Map<String, ClassWrapper> jobs = reflectionsWrapper.scan();
		assertThat(jobs.values(), hasSize(1));
		assertThat(jobs.keySet().iterator().next(), equalTo("FakeJob"));
		assertThat(jobs.values().iterator().next().newInstance(), equalTo(new FakeJob()));
	}

}
