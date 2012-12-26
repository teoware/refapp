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
import com.teoware.refapp.dao.dto.InsertUserRequest;
import com.teoware.refapp.dao.dto.SelectUserRequest;
import com.teoware.refapp.dao.dto.SelectUserResponse;
import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;
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
	private UserDao dao;

	@Override
	public RegisterUserResponse registerUser(RegisterUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Register user operation invoked.");

		try {
			Header header = request.getHeader();
			User user = request.getBody();
			UserPassword userPassword = request.getUserPassword();
			InsertUserRequest insertRequest = new InsertUserRequest(user, userPassword);

			dao.insertUser(insertRequest);

			return new RegisterUserResponse(header, new OperationResult(Result.SUCCESS, null));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Register user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public FindUserResponse findUser(FindUserRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Find user operation invoked.");

		try {
			Header header = request.getHeader();
			Username username = request.getBody();
			SelectUserRequest selectRequest = new SelectUserRequest(username);

			SelectUserResponse selectResposne = dao.selectUser(selectRequest);
			List<User> userList = selectResposne.getUserList();

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
			SelectUserResponse selectResponse = dao.selectAllUsers();

			return new ListUsersResponse(selectResponse.getUserList());
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": List users operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		}
	}
}
