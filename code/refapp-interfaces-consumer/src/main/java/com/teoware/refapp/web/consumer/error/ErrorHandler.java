package com.teoware.refapp.web.consumer.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.service.ServiceException;

public class ErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

    public static void handle(ServiceException e) {
        LOG.error("Service error occured", e);
        throw new RuntimeException(e);
    }
}
