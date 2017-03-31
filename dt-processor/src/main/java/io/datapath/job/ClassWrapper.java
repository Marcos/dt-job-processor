package io.datapath.job;

public class ClassWrapper {

	Class<? extends Job> clazz;

	public ClassWrapper(Class<? extends Job> clazz) {
		this.clazz = clazz;
	}

	public Job newInstance() throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}

}
