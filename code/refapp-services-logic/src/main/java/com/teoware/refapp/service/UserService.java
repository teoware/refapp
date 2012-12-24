package com.teoware.refapp.service;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.service.dto.FindUserRequest;
import com.teoware.refapp.service.dto.FindUserResponse;
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;

@Local
public interface UserService extends Serializable {

	public RegisterUserResponse registerUser(RegisterUserRequest request) throws ServiceException;

	public FindUserResponse findUser(FindUserRequest request) throws ServiceException;

	public ListUsersResponse listUsers() throws ServiceException;
}
