package com.teoware.refapp.batch.task;

public abstract class TaskSetup {

	protected TaskResult previousResult;

	public TaskResult getPreviousResult() {
		return previousResult;
	}

	public void setPreviousResult(TaskResult previousResult) {
		this.previousResult = previousResult;
	}
}
