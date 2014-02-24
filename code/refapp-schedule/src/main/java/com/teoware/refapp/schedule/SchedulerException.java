package com.teoware.refapp.schedule;

import com.teoware.refapp.RefappException;

public class SchedulerException extends RefappException {

    private static final long serialVersionUID = 1L;

    public SchedulerException() {
        super();
    }

    public SchedulerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchedulerException(String message) {
        super(message);
    }

    public SchedulerException(Throwable cause) {
        super(cause);
    }
}
