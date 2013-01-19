package com.teoware.refapp.web.consumer.vo;

public class FindUserRequestVO {

	private String username;

	public FindUserRequestVO() {
	}

	public FindUserRequestVO(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
