package com.teoware.refapp.batch.domain;

public abstract class TaskResult {

	protected Object data;
	protected Boolean terminate;

	protected TaskResult(Object data, Boolean terminate) {
		this.data = data;
		this.terminate = terminate;
	}

	public Object data() {
		return data;
	}

	public boolean terminate() {
		return terminate;
	}
}
