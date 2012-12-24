package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.DeleteUserRequest;
import com.teoware.refapp.dao.dto.DeleteUserResponse;
import com.teoware.refapp.dao.dto.InsertUserRequest;
import com.teoware.refapp.dao.dto.InsertUserResponse;
import com.teoware.refapp.dao.dto.PurgeUsersRequest;
import com.teoware.refapp.dao.dto.PurgeUsersResponse;
import com.teoware.refapp.dao.dto.SelectUserPasswordRequest;
import com.teoware.refapp.dao.dto.SelectUserPasswordResponse;
import com.teoware.refapp.dao.dto.SelectUserRequest;
import com.teoware.refapp.dao.dto.SelectUserResponse;
import com.teoware.refapp.dao.dto.UpdateUserRequest;
import com.teoware.refapp.dao.dto.UpdateUserResponse;

@Local
public interface UserDao extends Serializable {

	public InsertUserResponse insertUser(InsertUserRequest request) throws DaoException;

	public UpdateUserResponse updateUser(UpdateUserRequest request) throws DaoException;

	public SelectUserResponse selectUser(SelectUserRequest request) throws DaoException;

	public SelectUserResponse selectAllUsers() throws DaoException;

	public SelectUserPasswordResponse selectUserPassword(SelectUserPasswordRequest request) throws DaoException;

	public DeleteUserResponse deleteUser(DeleteUserRequest request) throws DaoException;

	public PurgeUsersResponse purgeUsers(PurgeUsersRequest request) throws DaoException;
}
