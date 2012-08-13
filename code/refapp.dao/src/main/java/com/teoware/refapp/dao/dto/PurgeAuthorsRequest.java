package com.teoware.refapp.dao.dto;

public class PurgeAuthorsRequest {

	private boolean greedy;

	public PurgeAuthorsRequest() {
	}

	public PurgeAuthorsRequest(boolean greedy) {
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
