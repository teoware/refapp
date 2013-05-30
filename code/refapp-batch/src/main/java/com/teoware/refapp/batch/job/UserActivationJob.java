package com.teoware.refapp.batch.job;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.teoware.refapp.batch.task.ActivatePendingUsersTask;
import com.teoware.refapp.batch.task.GetPendingUsersTask;

public class UserActivationJob extends BatchJob {

	@Inject
	private GetPendingUsersTask getPendingUsersTask;

	@Inject
	private ActivatePendingUsersTask activatePendingUsersTask;

	@Override
	@PostConstruct
	public void setup() {
		addTask(1, getPendingUsersTask);
		addTask(2, activatePendingUsersTask);
	}
}
