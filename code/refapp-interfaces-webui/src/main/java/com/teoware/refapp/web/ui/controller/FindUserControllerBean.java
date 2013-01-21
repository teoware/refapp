package com.teoware.refapp.web.ui.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;
import com.teoware.refapp.web.consumer.vo.FindUserResponseVO;

@Named
@RequestScoped
public class FindUserControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FindUserRequestVO vo;
	private User user;

	@Inject
	UserServiceConsumer consumer;

	@PostConstruct
	private void init() {
		setVo(new FindUserRequestVO());
	}

	public void onClickFindButton() {
		setDebug("onClickFindButton");
		FindUserResponseVO responseVO = consumer.findUser(vo);
		user = responseVO.getUser();
	}

	public FindUserRequestVO getVo() {
		return vo;
	}

	public void setVo(FindUserRequestVO vo) {
		this.vo = vo;
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
	public String getPageTitle() {
		return super.dict("page.find_user.title");
	}
}
