package com.teoware.refapp.service.validation;

public class InternalValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalValidationException() {
    }

    public InternalValidationException(Throwable cause) {
        super(cause);
    }

    public InternalValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalValidationException(String message) {
        super(message);
    }
}
