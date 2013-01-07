package com.teoware.refapp.web.consumer;

import javax.inject.Inject;

import com.teoware.refapp.model.user.Username;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindUserRequest;
import com.teoware.refapp.service.dto.FindUserResponse;
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.web.consumer.error.ErrorHandler;
import com.teoware.refapp.web.consumer.error.ValidationHandler;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserResponseVO;
import com.teoware.refapp.web.consumer.vo.UserListVO;
import com.teoware.refapp.web.consumer.vo.UserVO;
import com.teoware.refapp.web.consumer.vo.UsernameVO;

public class UserServiceConsumerBean implements UserServiceConsumer {

	@Inject
	private UserServiceFacade facade;

	@Override
	public RegisterUserResponseVO registerUser(RegisterUserRequestVO vo) {
		try {
			RegisterUserRequest request = new RegisterUserRequest(vo.getUser(), vo.getUserPassword());
			RegisterUserResponse response = facade.registerUser(request);
			return new RegisterUserResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public UserVO findUser(UsernameVO vo) {
		try {
			FindUserRequest request = new FindUserRequest(new Username(vo.getUsername()));
			FindUserResponse response = facade.findUser(request);
			return new UserVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public UserListVO listUsers() {
		try {
			ListUsersResponse response = facade.listUsers();
			return new UserListVO(response.getUserList());
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}
}
