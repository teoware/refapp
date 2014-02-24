package com.teoware.refapp.batch;

import com.teoware.refapp.RefappException;

public class BatchException extends RefappException {

    private static final long serialVersionUID = 1L;

    public BatchException() {
        super();
    }

    public BatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public BatchException(String message) {
        super(message);
    }

    public BatchException(Throwable cause) {
        super(cause);
    }
}
