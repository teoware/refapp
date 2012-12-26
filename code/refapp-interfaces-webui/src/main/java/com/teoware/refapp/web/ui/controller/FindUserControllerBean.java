package com.teoware.refapp.web.ui.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.Username;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;
import com.teoware.refapp.web.consumer.vo.UserVO;

@Named
@RequestScoped
public class FindUserControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_TITLE = "Find user";

	private String username;
	private User user;

	@Inject
	UserServiceConsumer consumer;

	public void onClickFindButton() {
		setDebug("onClickFindButton");
		FindUserRequestVO requestVO = createFindUserRequest();
		UserVO responseVO = consumer.findUser(requestVO);
		user = responseVO.getUser();
	}

	private FindUserRequestVO createFindUserRequest() {
		Username username = BeanFactory.createUsernameBean();
		username.setUsername(this.username);
		return new FindUserRequestVO(username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean hasUser() {
		return user != null;
	}

	@Override
	public String getTitle() {
		return super.getTitle(PAGE_TITLE);
	}
}
