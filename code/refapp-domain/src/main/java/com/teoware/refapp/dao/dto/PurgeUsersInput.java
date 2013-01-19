package com.teoware.refapp.dao.dto;

public class PurgeUsersInput {

	private boolean greedy;
	private int userActivationPeriod;

	public PurgeUsersInput() {
	}

	public PurgeUsersInput(boolean greedy, int userActivationPeriod) {
		this.setGreedy(greedy);
		this.setUserActivationPeriod(userActivationPeriod);
	}

	public boolean isGreedy() {
		return greedy;
	}

	public void setGreedy(boolean greedy) {
		this.greedy = greedy;
	}

	public int getUserActivationPeriod() {
		return userActivationPeriod;
	}

	public void setUserActivationPeriod(int userActivationPeriod) {
		this.userActivationPeriod = userActivationPeriod;
	}
}
