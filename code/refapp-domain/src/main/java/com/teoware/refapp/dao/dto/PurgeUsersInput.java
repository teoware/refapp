package com.teoware.refapp.dao.dto;

public class PurgeUsersInput {

	private boolean greedy;

	public PurgeUsersInput() {
	}

	public PurgeUsersInput(boolean greedy) {
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
