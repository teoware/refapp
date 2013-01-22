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
import com.teoware.refapp.dao.dto.CreateUserDetailsInput;
import com.teoware.refapp.dao.dto.CreateUserDetailsOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserAddressInput;
import com.teoware.refapp.dao.dto.DeleteUserAddressOutput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsInput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordInput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserStatusInput;
import com.teoware.refapp.dao.dto.DeleteUserStatusOutput;
import com.teoware.refapp.dao.dto.Id;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserAddressOutput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsInput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordInput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusOutput;
import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.UserStatus;
import com.teoware.refapp.model.user.Username;
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

			CreateUserDetailsInput createUserInfoInput = new CreateUserDetailsInput(userId, user.getUserDetails());
			CreateUserDetailsOutput createUserInfoOutput = dao.createUserDetails(createUserInfoInput);
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
	public ActivateUserResponse activateUser(ActivateUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Activate user operation invoked.");

		try {
			Header header = request.getHeader();
			Username username = request.getBody();

			dao.persistConnection();

			Id userId = dao.readUserId(username.getUsername());

			UpdateUserStatusInput updateUserStatusInput = new UpdateUserStatusInput(userId, new UserStatus(
					Status.ACTIVE, null, null));
			UpdateUserStatusOutput updateUserStatusOutput = dao.updateUserStatus(updateUserStatusInput);
			int rowsAffected = updateUserStatusOutput.getRowsAffected();

			return new ActivateUserResponse(header, new OperationResult(Result.SUCCESS, "<" + rowsAffected
					+ "> rows changed"));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Activate user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			dao.terminateConnection();
		}
	}

	@Override
	public SuspendUserResponse suspendUser(SuspendUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Suspend user operation invoked.");

		try {
			Header header = request.getHeader();
			Username username = request.getBody();

			dao.persistConnection();

			Id userId = dao.readUserId(username.getUsername());

			UpdateUserStatusInput updateUserStatusInput = new UpdateUserStatusInput(userId, new UserStatus(
					Status.SUSPENDED, null, null));
			UpdateUserStatusOutput updateUserStatusOutput = dao.updateUserStatus(updateUserStatusInput);
			int rowsAffected = updateUserStatusOutput.getRowsAffected();

			return new SuspendUserResponse(header, new OperationResult(Result.SUCCESS, "<" + rowsAffected
					+ "> rows changed"));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Suspend user operation failed.");
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
			LOG.error(SERVICE_NAME + ": Find user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			dao.terminateConnection();
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
		} finally {
			dao.terminateConnection();
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

			UpdateUserDetailsInput updateUserInfoInput = new UpdateUserDetailsInput(userId, request.getBody().getUserDetails());
			UpdateUserDetailsOutput updateUserInfoOutput = dao.updateUserDetails(updateUserInfoInput);
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
			LOG.error(SERVICE_NAME + ": Change user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			dao.terminateConnection();
		}
	}

	@Override
	public ChangeUserPasswordResponse changeUserPassword(ChangeUserPasswordRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Change user password operation invoked.");

		try {
			Header header = request.getHeader();
			UserPassword userPassword = request.getBody();
			Username username = request.getUsername();

			Id userId = dao.readUserId(username.getUsername());

			UpdateUserPasswordInput input = new UpdateUserPasswordInput(userId, userPassword);
			UpdateUserPasswordOutput output = dao.updateUserPassword(input);
			int rowsAffected = output.getRowsAffected();

			return new ChangeUserPasswordResponse(header, new OperationResult(Result.SUCCESS, "<" + rowsAffected
					+ "> rows changed"));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Change user password operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			dao.terminateConnection();
		}
	}

	@Override
	public DeleteUserResponse deleteUser(DeleteUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Delete user operation invoked.");

		try {
			int rowsAffected = 0;

			Header header = request.getHeader();
			Username username = request.getBody();

			dao.persistConnection();

			Id userId = dao.readUserId(username.getUsername());

			DeleteUserPasswordInput deleteUserPasswordInput = new DeleteUserPasswordInput(userId);
			DeleteUserPasswordOutput deleteUserPasswordOutput = dao.deleteUserPassword(deleteUserPasswordInput);
			rowsAffected += deleteUserPasswordOutput.getRowsAffected();

			DeleteUserAddressInput deleteUserAddressInput = new DeleteUserAddressInput(userId);
			DeleteUserAddressOutput deleteUserAddressOutput = dao.deleteUserAddress(deleteUserAddressInput);
			rowsAffected += deleteUserAddressOutput.getRowsAffected();

			DeleteUserStatusInput deleteUserStatusInput = new DeleteUserStatusInput(userId);
			DeleteUserStatusOutput deleteUserStatusOutput = dao.deleteUserStatus(deleteUserStatusInput);
			rowsAffected += deleteUserStatusOutput.getRowsAffected();

			DeleteUserDetailsInput deleteUserInfoInput = new DeleteUserDetailsInput(userId);
			DeleteUserDetailsOutput deleteUserInfoOutput = dao.deleteUserDetails(deleteUserInfoInput);
			rowsAffected += deleteUserInfoOutput.getRowsAffected();

			DeleteUserInput deleteUserInput = new DeleteUserInput(userId);
			DeleteUserOutput deleteUserOutput = dao.deleteUser(deleteUserInput);
			rowsAffected += deleteUserOutput.getRowsAffected();

			return new DeleteUserResponse(header, new OperationResult(Result.SUCCESS, "<" + rowsAffected
					+ "> rows deleted"));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Delete user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			dao.terminateConnection();
		}
	}
}
