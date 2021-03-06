package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;

public class RegisterUserRequestVO {

    private User user;
    private UserPassword userPassword;

    public RegisterUserRequestVO() {
    }

    public RegisterUserRequestVO(User user, UserPassword userPassword) {
        this.user = user;
        this.userPassword = userPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }
}
