package io.datapath.job;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class ReflectionsWrapper {

	@SuppressWarnings("unchecked")
	public Map<String, ClassWrapper> scan() {
		Map<String, ClassWrapper> scannedJobs = new HashMap<>();
		Set<Class<?>> jobs = new Reflections("io.datapath").getTypesAnnotatedWith(AnnotadedJob.class);
		jobs.forEach(j -> scannedJobs.put(j.getSimpleName(), new ClassWrapper((Class<Job>) j)));
		return scannedJobs;
	}

}
