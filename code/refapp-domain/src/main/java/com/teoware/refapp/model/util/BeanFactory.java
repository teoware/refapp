package com.teoware.refapp.model.util;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserAddress;
import com.teoware.refapp.model.user.UserId;
import com.teoware.refapp.model.user.UserInfo;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;

public class BeanFactory {

	public static Username createUsernameBean() {
		Username username = new Username();
		return username;
	}

	public static UserId createUserIdBean() {
		UserId userId = new UserId();
		return userId;
	}

	public static UserInfo createUserInfoBean() {
		UserInfo userInfo = new UserInfo();
		return userInfo;
	}

	public static UserAddress createUserAddressBean() {
		UserAddress userAddress = new UserAddress();
		return userAddress;
	}

	public static User createUserBean() {
		User user = new User();
		user.setUserId(createUserIdBean());
		user.setUserInfo(createUserInfoBean());
		user.setUserAddress(createUserAddressBean());
		return user;
	}

	public static UserPassword createUserPasswordBean() {
		return new UserPassword();
	}
}
