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
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUsersInput;
import com.teoware.refapp.dao.dto.ReadUsersOutput;
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
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.UserStatus;
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
import com.teoware.refapp.service.dto.FindUsersRequest;
import com.teoware.refapp.service.dto.FindUsersResponse;
import com.teoware.refapp.service.dto.ListUsersRequest;
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.dto.SuspendUserRequest;
import com.teoware.refapp.service.dto.SuspendUserResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserServiceBean extends Service implements UserService {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceBean.class);

    private static final String SERVICE_NAME = "User service";
    private static final String DAO_EXCEPTION_MESSAGE = "DAO exception";

    @Inject
    protected UserDao userDao;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Register user operation invoked.");

        try {
            int rowsAffected = 0;

            Header header = request.getHeader();
            User user = request.getBody();
            UserPassword userPassword = request.getUserPassword();

            userDao.persistConnection();

            CreateUserInput createUserInput = new CreateUserInput(user.getUsername());
            CreateUserOutput createUserOutput = userDao.createUser(createUserInput);
            Id userId = createUserOutput.getId();
            rowsAffected += createUserOutput.getRowsAffected();

            CreateUserDetailsInput createUserDetailsInput = new CreateUserDetailsInput(userId, user.getUserDetails());
            CreateUserDetailsOutput createUserDetailsOutput = userDao.createUserDetails(createUserDetailsInput);
            rowsAffected += createUserDetailsOutput.getRowsAffected();

            CreateUserAddressInput createUserAddressInput = new CreateUserAddressInput(userId, user.getUserAddress());
            CreateUserAddressOutput createUserAddressOutput = userDao.createUserAddress(createUserAddressInput);
            rowsAffected += createUserAddressOutput.getRowsAffected();

            CreateUserPasswordInput createUserPasswordInput = new CreateUserPasswordInput(createUserOutput.getId(),
                    userPassword);
            CreateUserPasswordOutput createUserPasswordOutput = userDao.createUserPassword(createUserPasswordInput);
            rowsAffected += createUserPasswordOutput.getRowsAffected();

            return new RegisterUserResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Register user operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    @Override
    public ActivateUserResponse activateUser(ActivateUserRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Activate user operation invoked.");

        try {
            Header header = request.getHeader();
            Username username = request.getBody();

            userDao.persistConnection();

            Id userId = userDao.readUserId(username);

            UpdateUserStatusInput updateUserStatusInput = new UpdateUserStatusInput(userId, new UserStatus(
                    Status.ACTIVE, null, null));
            UpdateUserStatusOutput updateUserStatusOutput = userDao.updateUserStatus(updateUserStatusInput);
            int rowsAffected = updateUserStatusOutput.getRowsAffected();

            return new ActivateUserResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Activate user operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    @Override
    public SuspendUserResponse suspendUser(SuspendUserRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Suspend user operation invoked.");

        try {
            Header header = request.getHeader();
            Username username = request.getBody();

            userDao.persistConnection();

            Id userId = userDao.readUserId(username);

            UpdateUserStatusInput updateUserStatusInput = new UpdateUserStatusInput(userId, new UserStatus(
                    Status.SUSPENDED, null, null));
            UpdateUserStatusOutput updateUserStatusOutput = userDao.updateUserStatus(updateUserStatusInput);
            int rowsAffected = updateUserStatusOutput.getRowsAffected();

            return new SuspendUserResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Suspend user operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    @Override
    public FindUserResponse findUser(FindUserRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Find user operation invoked.");

        try {
            Header header = request.getHeader();
            Username username = request.getBody();

            ReadUserInput readUserInput = new ReadUserInput(username);
            ReadUserOutput readUserOutput = userDao.readUser(readUserInput);
            List<User> userList = readUserOutput.getUserList();

            if (userList.size() == 0) {
                return new FindUserResponse(header, null);
            } else {
                // TODO Sanity check if more than one user found
                return new FindUserResponse(header, userList.get(0));
            }
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Find user operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    @Override
    public ListUsersResponse listUsers(ListUsersRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": List users operation invoked.");

        try {
            Header header = request.getHeader();
            ReadUsersInput readUsersInput = new ReadUsersInput(Status.ACTIVE);

            ReadUsersOutput readUsersOutput = userDao.readUsers(readUsersInput);

            return new ListUsersResponse(header, readUsersOutput.getUserList());
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": List users operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    public FindUsersResponse findPendigUsers(FindUsersRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Find pending users operation invoked.");

        try {
            Header header = request.getHeader();
            ReadUsersInput readUsersInput = new ReadUsersInput(Status.PENDING);

            ReadUsersOutput readUsersOutput = userDao.readUsers(readUsersInput);

            return new FindUsersResponse(header, readUsersOutput.getUserList());
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Find pending users operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    @Override
    public ChangeUserResponse changeUser(ChangeUserRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Change user operation invoked.");

        try {
            int rowsAffected = 0;

            Header header = request.getHeader();
            User user = request.getBody();

            userDao.persistConnection();

            Id userId = userDao.readUserId(user.getUsername());

            UpdateUserInput updateUserInput = new UpdateUserInput(userId, request.getUsername());
            UpdateUserOutput updateUserOutput = userDao.updateUser(updateUserInput);
            rowsAffected += updateUserOutput.getRowsAffected();

            UpdateUserDetailsInput updateUserDetailsInput = new UpdateUserDetailsInput(userId, request.getBody()
                    .getUserDetails());
            UpdateUserDetailsOutput updateUserDetailsOutput = userDao.updateUserDetails(updateUserDetailsInput);
            rowsAffected += updateUserDetailsOutput.getRowsAffected();

            UpdateUserStatusInput updateUserStatusInput = new UpdateUserStatusInput(userId, request.getBody()
                    .getUserStatus());
            UpdateUserStatusOutput updateUserStatusOutput = userDao.updateUserStatus(updateUserStatusInput);
            rowsAffected += updateUserStatusOutput.getRowsAffected();

            UpdateUserAddressInput updateUserAddressInput = new UpdateUserAddressInput(userId, request.getBody()
                    .getUserAddress());
            UpdateUserAddressOutput updateUserAddressOutput = userDao.updateUserAddress(updateUserAddressInput);
            rowsAffected += updateUserAddressOutput.getRowsAffected();

            return new ChangeUserResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Change user operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    @Override
    public ChangeUserPasswordResponse changeUserPassword(ChangeUserPasswordRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Change user password operation invoked.");

        try {
            Header header = request.getHeader();
            UserPassword userPassword = request.getBody();
            Username username = request.getUsername();

            Id userId = userDao.readUserId(username);

            UpdateUserPasswordInput input = new UpdateUserPasswordInput(userId, userPassword);
            UpdateUserPasswordOutput output = userDao.updateUserPassword(input);
            int rowsAffected = output.getRowsAffected();

            return new ChangeUserPasswordResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Change user password operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest request) throws ServiceException {
        LOG.info(SERVICE_NAME + ": Delete user operation invoked.");

        try {
            int rowsAffected = 0;

            Header header = request.getHeader();
            Username username = request.getBody();

            userDao.persistConnection();

            Id userId = userDao.readUserId(username);

            DeleteUserPasswordInput deleteUserPasswordInput = new DeleteUserPasswordInput(userId);
            DeleteUserPasswordOutput deleteUserPasswordOutput = userDao.deleteUserPassword(deleteUserPasswordInput);
            rowsAffected += deleteUserPasswordOutput.getRowsAffected();

            DeleteUserAddressInput deleteUserAddressInput = new DeleteUserAddressInput(userId);
            DeleteUserAddressOutput deleteUserAddressOutput = userDao.deleteUserAddress(deleteUserAddressInput);
            rowsAffected += deleteUserAddressOutput.getRowsAffected();

            DeleteUserStatusInput deleteUserStatusInput = new DeleteUserStatusInput(userId);
            DeleteUserStatusOutput deleteUserStatusOutput = userDao.deleteUserStatus(deleteUserStatusInput);
            rowsAffected += deleteUserStatusOutput.getRowsAffected();

            DeleteUserDetailsInput deleteUserDetailsInput = new DeleteUserDetailsInput(userId);
            DeleteUserDetailsOutput deleteUserDetailsOutput = userDao.deleteUserDetails(deleteUserDetailsInput);
            rowsAffected += deleteUserDetailsOutput.getRowsAffected();

            DeleteUserInput deleteUserInput = new DeleteUserInput(userId);
            DeleteUserOutput deleteUserOutput = userDao.deleteUser(deleteUserInput);
            rowsAffected += deleteUserOutput.getRowsAffected();

            return new DeleteUserResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
        } catch (DaoException e) {
            LOG.error(SERVICE_NAME + ": Delete user operation failed.");
            throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
        } finally {
            userDao.terminateConnection();
        }
    }
}
