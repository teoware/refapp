package com.teoware.refapp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.UserDao;
import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserAddressOutput;
import com.teoware.refapp.dao.dto.CreateUserInfoInput;
import com.teoware.refapp.dao.dto.CreateUserInfoOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.Id;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserAddressOutput;
import com.teoware.refapp.dao.dto.UpdateUserInfoInput;
import com.teoware.refapp.dao.dto.UpdateUserInfoOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusOutput;
import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;
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

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserServiceBean implements UserService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceBean.class);

	private static final String SERVICE_NAME = "User service";
	private static final String DAO_EXCEPTION_MESSAGE = "DAO exception";

	@Inject
	protected UserDao dao;

	@Override
	public RegisterUserResponse registerUser(RegisterUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Register user operation invoked.");

		try {
			int rowsAffected = 0;

			Header header = request.getHeader();
			User user = request.getBody();
			UserPassword userPassword = request.getUserPassword();

			dao.persistConnection();

			CreateUserInput createUserInput = new CreateUserInput(user.getUsername());
			CreateUserOutput createUserOutput = dao.createUser(createUserInput);
			Id userId = createUserOutput.getUserId();
			rowsAffected += createUserOutput.getRowsAffected();

			CreateUserInfoInput createUserInfoInput = new CreateUserInfoInput(userId, user.getUserInfo());
			CreateUserInfoOutput createUserInfoOutput = dao.createUserInfo(createUserInfoInput);
			rowsAffected += createUserInfoOutput.getRowsAffected();

			CreateUserAddressInput createUserAddressInput = new CreateUserAddressInput(userId, user.getUserAddress());
			CreateUserAddressOutput createUserAddressOutput = dao.createUserAddress(createUserAddressInput);
			rowsAffected += createUserAddressOutput.getRowsAffected();

			CreateUserPasswordInput createUserPasswordInput = new CreateUserPasswordInput(createUserOutput.getUserId(),
					userPassword);
			CreateUserPasswordOutput createUserPasswordOutput = dao.createUserPassword(createUserPasswordInput);
			rowsAffected += createUserPasswordOutput.getRowsAffected();

			return new RegisterUserResponse(header, new OperationResult(Result.SUCCESS, "<" + rowsAffected
					+ "> rows created"));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Register user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			dao.terminateConnection();
		}
	}

	@Override
	public FindUserResponse findUser(FindUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Find user operation invoked.");

		try {
			Header header = request.getHeader();
			Username username = request.getBody();
			ReadUserInput readUserInput = new ReadUserInput(username);

			ReadUserOutput readUserOutput = dao.readUser(readUserInput);
			List<User> userList = readUserOutput.getUserList();

			// TODO Sanity check if more than one user found

			return new FindUserResponse(header, userList.get(0));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Register user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public ListUsersResponse listUsers() throws ServiceException {
		LOG.info(SERVICE_NAME + ": List users operation invoked.");

		try {
			ReadUserOutput readUserOutput = dao.readAllUsers();

			return new ListUsersResponse(readUserOutput.getUserList());
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": List users operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public ChangeUserResponse changeUser(ChangeUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Change user operation invoked.");

		try {
			int rowsAffected = 0;

			Header header = request.getHeader();
			User user = request.getBody();

			dao.persistConnection();

			Id userId = dao.readUserId(user.getUsername().getUsername());

			UpdateUserInput updateUserInput = new UpdateUserInput(userId, request.getUsername());
			UpdateUserOutput updateUserOutput = dao.updateUser(updateUserInput);
			rowsAffected += updateUserOutput.getRowsAffected();

			UpdateUserInfoInput updateUserInfoInput = new UpdateUserInfoInput(userId, request.getBody().getUserInfo());
			UpdateUserInfoOutput updateUserInfoOutput = dao.updateUserInfo(updateUserInfoInput);
			rowsAffected += updateUserInfoOutput.getRowsAffected();

			UpdateUserStatusInput updateUserStatusInput = new UpdateUserStatusInput(userId, request.getBody()
					.getUserStatus());
			UpdateUserStatusOutput updateUserStatusOutput = dao.updateUserStatus(updateUserStatusInput);
			rowsAffected += updateUserStatusOutput.getRowsAffected();

			UpdateUserAddressInput updateUserAddressInput = new UpdateUserAddressInput(userId, request.getBody()
					.getUserAddress());
			UpdateUserAddressOutput updateUserAddressOutput = dao.updateUserAddress(updateUserAddressInput);
			rowsAffected += updateUserAddressOutput.getRowsAffected();

			return new ChangeUserResponse(header, new OperationResult(Result.SUCCESS, "<" + rowsAffected
					+ "> rows changed"));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Register user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			dao.terminateConnection();
		}
	}

	@Override
	public ChangeUserPasswordResponse changeUserPassword(ChangeUserPasswordRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Change user password operation invoked.");
		return null;
	}

	@Override
	public DeleteUserResponse deleteUser(DeleteUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Delete user operation invoked.");
		return null;
	}
}
