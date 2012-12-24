package com.teoware.refapp.dao.dto;

public class PurgeUsersRequest {

	private boolean greedy;

	public PurgeUsersRequest() {
	}

	public PurgeUsersRequest(boolean greedy) {
		super();
		this.greedy = greedy;
	}

	public boolean isGreedy() {
		return greedy;
	}

	public void setGreedy(boolean greedy) {
		this.greedy = greedy;
	}
}
