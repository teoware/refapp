package com.teoware.refapp.batch.task;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.task.ActivatePendingUsersResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.ActivateUserRequest;
import com.teoware.refapp.service.dto.ActivateUserResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;
import com.teoware.refapp.service.validation.ValidationException;

public class ActivatePendingUsersTask extends BatchTask<Integer, List<User>> {

	private static final Logger LOG = LoggerFactory.getLogger(ActivatePendingUsersTask.class);

	@Inject
	private UserServiceFacade facade;

	@Override
	public TaskSetup<List<User>> init() {
		return new ActivatePendingUsersSetup();
	}

	@Override
	public void run() {
		try {
			LOG.info("Activating pending users");
			List<User> pendingUsersList = setup.getPreviousResult().data();
			int activatedUsers = 0;
			for (User pendingUser : pendingUsersList) {
				LOG.info("Activating user '{}'", pendingUser.getUsername().getUsername());
				ActivateUserRequest request = new ActivateUserRequest(pendingUser.getUsername());
				ActivateUserResponse response = facade.activateUser(request);
				if (Result.SUCCESS == response.getBody().getResult()) {
					activatedUsers++;
				}
			}
			result = new ActivatePendingUsersResult(activatedUsers, Boolean.FALSE);
		} catch (ServiceException e) {
			throw new BatchException("Service error occured when activating pending users", e);
		} catch (ValidationException e) {
			throw new BatchException("Validation error occured when activating pending users", e);
		}
	}
}
