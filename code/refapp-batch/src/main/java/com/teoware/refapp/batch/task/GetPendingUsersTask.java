package com.teoware.refapp.batch.task;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindUsersRequest;
import com.teoware.refapp.service.dto.FindUsersResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;

public class GetPendingUsersTask extends BatchTask<List<User>, Object> {

	private static final Logger LOG = LoggerFactory.getLogger(GetPendingUsersTask.class);

	@Inject
	private UserServiceFacade facade;

	@Override
	public TaskSetup<Object> init() {
		return null;
	}

	@Override
	public void run() {
		try {
			LOG.info("Getting pending users");
			FindUsersResponse response = facade.findPendigUsers(new FindUsersRequest());
			List<User> pendingUsersList = response.getBody();
			boolean terminate = pendingUsersList.size() == 0;
			LOG.info("{} pending user(s) found", pendingUsersList.size());
			result = new GetPendingUsersResult(pendingUsersList, terminate);
		} catch (ServiceException e) {
			throw new BatchException("Service error occured when finding pending users", e);
		}
	}
}
