package io.datapath.jobs;

import io.datapath.job.AnnotadedJob;
import io.datapath.job.Job;

@AnnotadedJob
public class FailImmediately implements Job{

	@Override
	public void execute() {
		throw new RuntimeException("FailImmediately");
	}

}
