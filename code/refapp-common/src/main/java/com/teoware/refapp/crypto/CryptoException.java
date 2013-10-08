package com.teoware.refapp.crypto;

import com.teoware.refapp.RefappException;

public class CryptoException extends RefappException {

	private static final long serialVersionUID = 1L;

	public CryptoException() {
		super();
	}

	public CryptoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CryptoException(String message) {
		super(message);
	}

	public CryptoException(Throwable cause) {
		super(cause);
	}
}
