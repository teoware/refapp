package com.teoware.refapp.service;

import java.io.Serializable;

import javax.ejb.Local;

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

@Local
public interface UserService extends Serializable {

    public RegisterUserResponse registerUser(RegisterUserRequest request) throws ServiceException;

    public ActivateUserResponse activateUser(ActivateUserRequest request) throws ServiceException;

    public SuspendUserResponse suspendUser(SuspendUserRequest request) throws ServiceException;

    public FindUserResponse findUser(FindUserRequest request) throws ServiceException;

    public ListUsersResponse listUsers(ListUsersRequest request) throws ServiceException;

    public FindUsersResponse findPendigUsers(FindUsersRequest request) throws ServiceException;

    public ChangeUserResponse changeUser(ChangeUserRequest request) throws ServiceException;

    public ChangeUserPasswordResponse changeUserPassword(ChangeUserPasswordRequest request) throws ServiceException;

    public DeleteUserResponse deleteUser(DeleteUserRequest request) throws ServiceException;
}
