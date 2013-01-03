package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.PurgeUsersOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;

@Local
public interface UserDao extends Serializable {

	public CreateUserOutput createUser(CreateUserInput request) throws DaoException;

	public ReadUserOutput readUser(ReadUserInput request) throws DaoException;

	public ReadUserOutput readAllUsers() throws DaoException;

	public ReadUserPasswordOutput readUserPassword(ReadUserPasswordInput request) throws DaoException;

	public UpdateUserOutput updateUser(UpdateUserInput request) throws DaoException;

	public DeleteUserOutput deleteUser(DeleteUserInput request) throws DaoException;

	public PurgeUsersOutput purgeUsers(PurgeUsersInput request) throws DaoException;
}
