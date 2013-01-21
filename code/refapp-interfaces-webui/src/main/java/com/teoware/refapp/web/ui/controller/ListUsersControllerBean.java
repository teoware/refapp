package com.teoware.refapp.web.ui.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.ListUsersVO;

@Named
@RequestScoped
public class ListUsersControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ListUsersControllerBean.class);

	private List<User> userList;

	@Inject
	UserServiceConsumer consumer;

	public void doLoadUserList() {
		LOG.debug("Loading user list");
		ListUsersVO vo = consumer.listUsers();
		userList = vo.getUserList();
		debug = "Size: " + userList.size();
	}

	public List<User> getUserList() {
		return userList;
	}

	@Override
	public String getPageTitle() {
		return super.dict("page.list_users.title");
	}
}
