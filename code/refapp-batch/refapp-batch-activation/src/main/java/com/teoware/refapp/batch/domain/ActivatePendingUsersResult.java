package com.teoware.refapp.batch.domain;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class ActivatePendingUsersResult extends TaskResult {

    public ActivatePendingUsersResult(List<User> data, Boolean terminate) {
        super(data, terminate);
    }
}
