package com.teoware.refapp.batch.domain;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class GetPendingUsersResult extends TaskResult {

    public GetPendingUsersResult(List<User> pendingUsersList, Boolean terminate) {
        super(pendingUsersList, terminate);
    }
}
