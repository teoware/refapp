package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.user.UserStatus;

public class UpdateUserStatusInput extends ChangeInput {

    private UserStatus userStatus;

    public UpdateUserStatusInput() {
        super();
    }

    public UpdateUserStatusInput(Id id, UserStatus userStatus) {
        super(id);
        this.userStatus = userStatus;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
