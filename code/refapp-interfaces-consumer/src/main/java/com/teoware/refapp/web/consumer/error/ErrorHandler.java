package com.teoware.refapp.web.consumer.error;

import com.teoware.refapp.service.ServiceException;

public class ErrorHandler {

	public static void handle(ServiceException e) {
		throw new RuntimeException(e);
	}
}
