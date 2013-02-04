package com.teoware.refapp.service.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.UserService;
import com.teoware.refapp.service.dto.ActivateUserRequest;
import com.teoware.refapp.service.dto.ActivateUserResponse;
import com.teoware.refapp.service.dto.ChangeUserPasswordRequest;
import com.teoware.refapp.service.dto.ChangeUserPasswordResponse;
import com.teoware.refapp.service.dto.ChangeUserRequest;
import com.teoware.refapp.service.dto.ChangeUserResponse;
import com.teoware.refapp.service.dto.DeleteUserRequest;
import com.teoware.refapp.service.dto.DeleteUserResponse;
import com.teoware.refapp.service.dto.FindUserRequest;
import com.teoware.refapp.service.dto.FindUserResponse;
import com.teoware.refapp.service.dto.ListUsersRequest;
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.dto.SuspendUserRequest;
import com.teoware.refapp.service.dto.SuspendUserResponse;
import com.teoware.refapp.service.validation.Validate;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.service.validation.ValidationInterceptor;
import com.teoware.refapp.service.validation.group.ActivateUserRequestGroup;
import com.teoware.refapp.service.validation.group.ChangeUserPasswordRequestGroup;
import com.teoware.refapp.service.validation.group.ChangeUserRequestGroup;
import com.teoware.refapp.service.validation.group.DeleteUserRequestGroup;
import com.teoware.refapp.service.validation.group.FindUserRequestGroup;
import com.teoware.refapp.service.validation.group.RegisterUserRequestGroup;
import com.teoware.refapp.service.validation.group.SuspendUserRequestGroup;

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

	@Interceptors({ ValidationInterceptor.class })
	@Validate(ActivateUserRequestGroup.class)
	@Override
	public ActivateUserResponse activateUser(@Validate ActivateUserRequest request) throws ValidationException,
			ServiceException {
		return service.activateUser(request);
	}

	@Interceptors({ ValidationInterceptor.class })
	@Validate(SuspendUserRequestGroup.class)
	@Override
	public SuspendUserResponse suspendUser(@Validate SuspendUserRequest request) throws ValidationException,
			ServiceException {
		return service.suspendUser(request);
	}

	@Interceptors({ ValidationInterceptor.class })
	@Validate(FindUserRequestGroup.class)
	@Override
	public FindUserResponse findUser(FindUserRequest request) throws ValidationException, ServiceException {
		return service.findUser(request);
	}

	@Override
	public ListUsersResponse listUsers(ListUsersRequest request) throws ServiceException {
		return service.listUsers(request);
	}

	@Interceptors({ ValidationInterceptor.class })
	@Validate(ChangeUserRequestGroup.class)
	@Override
	public ChangeUserResponse changeUser(ChangeUserRequest request) throws ValidationException, ServiceException {
		return service.changeUser(request);
	}

	@Interceptors({ ValidationInterceptor.class })
	@Validate(ChangeUserPasswordRequestGroup.class)
	@Override
	public ChangeUserPasswordResponse changeUserPassword(ChangeUserPasswordRequest request) throws ValidationException,
			ServiceException {
		return service.changeUserPassword(request);
	}

	@Interceptors({ ValidationInterceptor.class })
	@Validate(DeleteUserRequestGroup.class)
	@Override
	public DeleteUserResponse deleteUser(DeleteUserRequest request) throws ValidationException, ServiceException {
		return service.deleteUser(request);
	}
}
