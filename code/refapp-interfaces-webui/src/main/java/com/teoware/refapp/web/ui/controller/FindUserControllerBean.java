package com.teoware.refapp.web.ui.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.FindUserResponseVO;
import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;

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
		FindUserRequestVO vo = createFindUserRequest();
		FindUserResponseVO responseVO = consumer.findUser(vo);
		user = responseVO.getUser();
	}

	private FindUserRequestVO createFindUserRequest() {
		return new FindUserRequestVO(this.username);
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
