package com.teoware.refapp.web.consumer.error;

import static java.lang.System.out;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.service.ServiceException;

public class ErrorHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

	public static void handle(ServiceException e) {
		LOG.debug("Service error occured");
		out.println("Service error occured");
		throw new RuntimeException(e);
	}
}
