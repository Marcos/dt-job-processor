package io.datapath.jobs;

import io.datapath.job.AnnotadedJob;
import io.datapath.job.Job;

@AnnotadedJob
public class CountWords implements Job{

	@Override
	public void execute() {
		System.out.println("Counting words");
	}

}
