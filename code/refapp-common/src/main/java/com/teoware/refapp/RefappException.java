package com.teoware.refapp;

public class RefappException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RefappException() {
		super();
	}

	public RefappException(String message, Throwable cause) {
		super(message, cause);
	}

	public RefappException(String message) {
		super(message);
	}

	public RefappException(Throwable cause) {
		super(cause);
	}
}
