package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

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
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.PurgeUsersOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
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
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Username;

@Local
public interface UserDao extends Serializable {

	public CreateUserOutput createUser(CreateUserInput input) throws DaoException;

	public CreateUserDetailsOutput createUserDetails(CreateUserDetailsInput input) throws DaoException;

	public CreateUserAddressOutput createUserAddress(CreateUserAddressInput input) throws DaoException;

	public CreateUserPasswordOutput createUserPassword(CreateUserPasswordInput input) throws DaoException;

	public Id readUserId(Username username) throws DaoException;

	public ReadUserOutput readUser(ReadUserInput input) throws DaoException;

	public ReadUsersOutput readUsers(ReadUsersInput input) throws DaoException;

	public ReadUserPasswordOutput readUserPassword(ReadUserPasswordInput input) throws DaoException;

	public UpdateUserOutput updateUser(UpdateUserInput input) throws DaoException;

	public UpdateUserDetailsOutput updateUserDetails(UpdateUserDetailsInput input) throws DaoException;

	public UpdateUserStatusOutput updateUserStatus(UpdateUserStatusInput input) throws DaoException;

	public UpdateUserAddressOutput updateUserAddress(UpdateUserAddressInput input) throws DaoException;

	public UpdateUserPasswordOutput updateUserPassword(UpdateUserPasswordInput input) throws DaoException;

	public DeleteUserOutput deleteUser(DeleteUserInput input) throws DaoException;

	public DeleteUserDetailsOutput deleteUserDetails(DeleteUserDetailsInput input) throws DaoException;

	public DeleteUserStatusOutput deleteUserStatus(DeleteUserStatusInput input) throws DaoException;

	public DeleteUserAddressOutput deleteUserAddress(DeleteUserAddressInput input) throws DaoException;

	public DeleteUserPasswordOutput deleteUserPassword(DeleteUserPasswordInput input) throws DaoException;

	public PurgeUsersOutput purgeUsers(PurgeUsersInput input) throws DaoException;

	public void persistConnection();

	public void terminateConnection();
}
