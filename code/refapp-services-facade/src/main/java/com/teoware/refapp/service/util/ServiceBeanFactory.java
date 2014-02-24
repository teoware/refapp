package com.teoware.refapp.service.util;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.service.dto.RegisterUserRequest;

public class ServiceBeanFactory {

    public static RegisterUserRequest createRegisterUserRequestBean() {
        User user = BeanFactory.createUserBean();
        UserPassword userPassword = BeanFactory.createUserPasswordBean();
        return new RegisterUserRequest(user, userPassword);
    }
}
