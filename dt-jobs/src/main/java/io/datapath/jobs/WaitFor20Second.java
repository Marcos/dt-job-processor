package io.datapath.jobs;

import io.datapath.job.AnnotadedJob;
import io.datapath.job.Job;

@AnnotadedJob
public class WaitFor20Second implements Job{

	@Override
	public void execute() {
		System.out.println("Wait for 20 seconds");
		try {
			Thread.sleep(20*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
