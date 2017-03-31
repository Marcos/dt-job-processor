package io.datapath.job.exceptions;

public class JobNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2926789152454258151L;

	public JobNotFoundException(Exception e) {
		super(e);
	}

}
