package com.teoware.refapp.timer;

import com.teoware.refapp.RefappException;

public class TimerException extends RefappException {

	private static final long serialVersionUID = 1L;

	public TimerException() {
		super();
	}

	public TimerException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimerException(String message) {
		super(message);
	}

	public TimerException(Throwable cause) {
		super(cause);
	}
}
