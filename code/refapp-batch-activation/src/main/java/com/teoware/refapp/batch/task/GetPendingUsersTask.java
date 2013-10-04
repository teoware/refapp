package com.teoware.refapp.batch.task;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.domain.GetPendingUsersResult;
import com.teoware.refapp.batch.domain.TaskResult;
import com.teoware.refapp.batch.domain.TaskSetup;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindUsersRequest;
import com.teoware.refapp.service.dto.FindUsersResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;

public class GetPendingUsersTask extends BatchTask<List<User>> {

	private static final Logger LOG = LoggerFactory.getLogger(GetPendingUsersTask.class);

	@Inject
	private UserServiceFacade userServiceFacade;

	@Override
	public TaskSetup setup(Object data) {
		return null;
	}

	@Override
	public TaskResult run(TaskSetup setup) {
		try {
			LOG.info("Getting pending users");
			FindUsersResponse response = userServiceFacade.findPendigUsers(new FindUsersRequest());
			List<User> pendingUsersList = response.getBody();
			boolean terminate = pendingUsersList.size() == 0;
			LOG.info("{} pending user(s) found", pendingUsersList.size());
			return new GetPendingUsersResult(pendingUsersList, terminate);
		} catch (ServiceException e) {
			throw new BatchException("Service error occured when finding pending users", e);
		}
	}
}
