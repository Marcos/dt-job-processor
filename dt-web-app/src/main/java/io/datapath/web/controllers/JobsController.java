package io.datapath.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.datapath.job.JobExecutor;
import io.datapath.job.exceptions.JobNotFoundException;

@RestController
@RequestMapping(value = "jobs")
public class JobsController {

	@Autowired
	private JobExecutor jobExecutor;

	@RequestMapping(method = RequestMethod.POST, value = "/{job}")
	public void execute(@PathVariable(value = "job") String job) {
		jobExecutor.addToQueue(job);
	}

	@RequestMapping(method = RequestMethod.POST, value = "workers/size/{size}")
	public void setWorkersSize(@PathVariable(value = "size") int size) {
		jobExecutor.setWorkersSize(size);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Job not found.")
	@ExceptionHandler(JobNotFoundException.class)
	public void handleNotFound() {

	}

}
