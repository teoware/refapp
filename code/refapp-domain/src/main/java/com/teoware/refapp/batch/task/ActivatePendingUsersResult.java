package com.teoware.refapp.batch.task;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class ActivatePendingUsersResult extends TaskResult<List<User>> {

	protected ActivatePendingUsersResult(List<User> data, Boolean terminate) {
		super(data, terminate);
	}
}
