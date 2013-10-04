package com.teoware.refapp.batch.task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.domain.ActivatePendingUsersResult;
import com.teoware.refapp.batch.domain.ActivatePendingUsersSetup;
import com.teoware.refapp.batch.domain.TaskResult;
import com.teoware.refapp.batch.domain.TaskSetup;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.ActivateUserRequest;
import com.teoware.refapp.service.dto.ActivateUserResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;
import com.teoware.refapp.service.validation.ValidationException;

public class ActivatePendingUsersTask extends BatchTask<List<User>> {

	private static final Logger LOG = LoggerFactory.getLogger(ActivatePendingUsersTask.class);

	@Inject
	private UserServiceFacade userServiceFacade;

	@Override
	public TaskSetup setup(Object data) {
		List<User> userList = convert(data);
		return new ActivatePendingUsersSetup(userList);
	}

	@Override
	public TaskResult run(TaskSetup setup) {
		try {
			LOG.info("Activating pending users");
			List<User> pendingUsersList = convert(setup.data());
			List<User> activatedUsers = new ArrayList<User>();
			for (User pendingUser : pendingUsersList) {
				LOG.info("Activating user '{}'", pendingUser.getUsername().getUsername());
				ActivateUserRequest request = new ActivateUserRequest(pendingUser.getUsername());
				ActivateUserResponse response = userServiceFacade.activateUser(request);
				if (Result.SUCCESS == response.getBody().getResult()) {
					activatedUsers.add(pendingUser);
				}
			}
			boolean terminate = activatedUsers.size() == 0;
			return new ActivatePendingUsersResult(activatedUsers, terminate);
		} catch (ServiceException e) {
			throw new BatchException("Service error occured when activating pending users", e);
		} catch (ValidationException e) {
			throw new BatchException("Validation error occured when activating pending users", e);
		}
	}
}
