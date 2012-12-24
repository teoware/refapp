package com.teoware.refapp.service.facade;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindUserRequest;
import com.teoware.refapp.service.dto.FindUserResponse;
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.validation.ValidationException;

@Local
public interface UserServiceFacade extends Serializable {

	public RegisterUserResponse registerUser(RegisterUserRequest request) throws ValidationException,
			ServiceException;

	public FindUserResponse findUser(FindUserRequest request) throws ValidationException, ServiceException;

	public ListUsersResponse listUsers() throws ServiceException;
}
