package com.teoware.refapp.batch.task;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class GetPendingUsersResult extends TaskResult<List<User>> {

	public GetPendingUsersResult(List<User> pendingUsersList, Boolean terminate) {
		super(pendingUsersList, terminate);
	}
}
