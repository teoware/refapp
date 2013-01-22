package com.teoware.refapp.web.consumer;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.service.ServiceException;
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
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.dto.SuspendUserRequest;
import com.teoware.refapp.service.dto.SuspendUserResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.util.PasswordHandler;
import com.teoware.refapp.web.consumer.error.ErrorHandler;
import com.teoware.refapp.web.consumer.error.ValidationHandler;
import com.teoware.refapp.web.consumer.vo.ActivateUserRequestVO;
import com.teoware.refapp.web.consumer.vo.ActivateUserResponseVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserPasswordRequestVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserPasswordResponseVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserRequestVO;
import com.teoware.refapp.web.consumer.vo.ChangeUserResponseVO;
import com.teoware.refapp.web.consumer.vo.DeleteUserRequestVO;
import com.teoware.refapp.web.consumer.vo.DeleteUserResponseVO;
import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;
import com.teoware.refapp.web.consumer.vo.FindUserResponseVO;
import com.teoware.refapp.web.consumer.vo.ListUsersVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserResponseVO;
import com.teoware.refapp.web.consumer.vo.SuspendUserRequestVO;
import com.teoware.refapp.web.consumer.vo.SuspendUserResponseVO;

public class UserServiceConsumerBean implements UserServiceConsumer {

	@Inject
	private UserServiceFacade facade;

	@Override
	public RegisterUserResponseVO registerUser(RegisterUserRequestVO vo) {
		try {
			RegisterUserRequest request = processRegisterUserRequest(vo);

			RegisterUserResponse response = facade.registerUser(request);
			return new RegisterUserResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; // For compiler
	}

	private RegisterUserRequest processRegisterUserRequest(RegisterUserRequestVO vo)
			throws UnsupportedEncodingException {
		RegisterUserRequest request = new RegisterUserRequest(vo.getUser(), vo.getUserPassword());

		String salt = PasswordHandler.generateSalt();
		request.getUserPassword().setSalt(salt);

		String encryptedPassword = PasswordHandler.encryptPassword(vo.getUserPassword().getPassword(), salt);
		request.getUserPassword().setPassword(encryptedPassword);

		return request;
	}

	@Override
	public ActivateUserResponseVO activateUser(ActivateUserRequestVO vo) {
		try {
			ActivateUserRequest request = new ActivateUserRequest(vo.getUsername());
			ActivateUserResponse response = facade.activateUser(request);
			return new ActivateUserResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public SuspendUserResponseVO suspendUser(SuspendUserRequestVO vo) {
		try {
			SuspendUserRequest request = new SuspendUserRequest(vo.getUsername());
			SuspendUserResponse response = facade.suspendUser(request);
			return new SuspendUserResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public FindUserResponseVO findUser(FindUserRequestVO vo) {
		try {
			FindUserRequest request = new FindUserRequest(new Username(vo.getUsername()));
			FindUserResponse response = facade.findUser(request);
			return new FindUserResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public ListUsersVO listUsers() {
		try {
			ListUsersResponse response = facade.listUsers();
			return new ListUsersVO(response.getUserList());
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public ChangeUserResponseVO changeUser(ChangeUserRequestVO vo) {
		try {
			ChangeUserRequest request = new ChangeUserRequest(vo.getUser(), vo.getUsername());
			ChangeUserResponse response = facade.changeUser(request);
			return new ChangeUserResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public ChangeUserPasswordResponseVO changeUserPassword(ChangeUserPasswordRequestVO vo) {
		try {
			ChangeUserPasswordRequest request = new ChangeUserPasswordRequest(vo.getUserPassword(), vo.getUsername());
			ChangeUserPasswordResponse response = facade.changeUserPassword(request);
			return new ChangeUserPasswordResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}

	@Override
	public DeleteUserResponseVO deleteUser(DeleteUserRequestVO vo) {
		try {
			DeleteUserRequest request = new DeleteUserRequest(vo.getUsername());
			DeleteUserResponse response = facade.deleteUser(request);
			return new DeleteUserResponseVO(response.getBody());
		} catch (ValidationException e) {
			ValidationHandler.handle(e);
		} catch (ServiceException e) {
			ErrorHandler.handle(e);
		}
		return null; // For compiler
	}
}
