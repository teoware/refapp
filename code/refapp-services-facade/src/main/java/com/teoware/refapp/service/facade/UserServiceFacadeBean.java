package com.teoware.refapp.service.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.teoware.refapp.service.UserService;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindUserRequest;
import com.teoware.refapp.service.dto.FindUserResponse;
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.validation.Validate;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.service.validation.ValidationInterceptor;
import com.teoware.refapp.service.validation.group.RegisterUserRequestGroup;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserServiceFacadeBean implements UserServiceFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService service;

	@Interceptors({ ValidationInterceptor.class })
	@Validate(RegisterUserRequestGroup.class)
	@Override
	public RegisterUserResponse registerUser(@Validate RegisterUserRequest request) throws ValidationException,
			ServiceException {
		return service.registerUser(request);
	}

	@Override
	public FindUserResponse findUser(FindUserRequest request) throws ValidationException, ServiceException {
		return service.findUser(request);
	}

	@Override
	public ListUsersResponse listUsers() throws ServiceException {
		return service.listUsers();
	}

}
