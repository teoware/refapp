package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.PurgeUsersOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordInput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordOutput;

@Local
public interface UserDao extends Serializable {

	public CreateUserOutput createUser(CreateUserInput input) throws DaoException;

	public CreateUserPasswordOutput createUserPassword(CreateUserPasswordInput input) throws DaoException;

	public ReadUserOutput readUser(ReadUserInput input) throws DaoException;

	public ReadUserOutput readAllUsers() throws DaoException;

	public ReadUserPasswordOutput readUserPassword(ReadUserPasswordInput input) throws DaoException;

	public UpdateUserOutput updateUser(UpdateUserInput input) throws DaoException;

	public UpdateUserPasswordOutput updateUserPassword(UpdateUserPasswordInput input) throws DaoException;

	public DeleteUserOutput deleteUser(DeleteUserInput input) throws DaoException;

	public PurgeUsersOutput purgeUsers(PurgeUsersInput input) throws DaoException;
}
