package com.teoware.refapp.batch.task;

public class TaskTerminatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskTerminatedException() {
		super();
	}

	public TaskTerminatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskTerminatedException(String message) {
		super(message);
	}

	public TaskTerminatedException(Throwable cause) {
		super(cause);
	}
}
