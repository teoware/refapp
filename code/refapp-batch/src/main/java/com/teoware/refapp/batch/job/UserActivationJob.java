package com.teoware.refapp.batch.job;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.teoware.refapp.batch.task.ActivatePendingUsersTask;
import com.teoware.refapp.batch.task.GetPendingUsersTask;
import com.teoware.refapp.batch.task.NotifyActivatedUsersTask;

public class UserActivationJob extends BatchJob {

	@Inject
	private GetPendingUsersTask getPendingUsersTask;

	@Inject
	private ActivatePendingUsersTask activatePendingUsersTask;

	@Inject
	private NotifyActivatedUsersTask notifyActivatedUsersTask;

	@PostConstruct
	@Override
	protected void setup() {
		addTask(getPendingUsersTask);
		addTask(activatePendingUsersTask);
		addTask(notifyActivatedUsersTask);
	}
}
