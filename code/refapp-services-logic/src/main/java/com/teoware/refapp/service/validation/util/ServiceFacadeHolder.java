package com.teoware.refapp.service.validation.util;

public class ServiceFacadeHolder {

	private ServiceFacadeHolder() {
	}
	
	private static final ThreadLocal<ServiceFacade> serviceFacade = new ThreadLocal<ServiceFacade>();
	
	public static ServiceFacade getServiceFacade() {
		return serviceFacade.get();
	}
	
	public static void setServiceFacade( ServiceFacade serviceFacade ) {
		ServiceFacadeHolder.serviceFacade.set( serviceFacade );
	}
}
