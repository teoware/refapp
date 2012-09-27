package com.teoware.refapp.service.validation.mock;

import java.util.List;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.interceptor.ValidationInterceptor;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.service.validation.group.ValidationGroup;

public class ValidationInterceptorMock extends ValidationInterceptor {

	public void validateParams(List<? super Object> list, Class<? extends ValidationGroup> group) throws ValidationException, ServiceException {
		super.validateParams(list, group);
	}
}
