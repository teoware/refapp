package com.teoware.refapp.batch.task;

public abstract class TaskSetup<T> {

	protected TaskResult<T> previousResult;

	public TaskResult<T> getPreviousResult() {
		return previousResult;
	}

	public void setPreviousResult(TaskResult<T> previousResult) {
		this.previousResult = previousResult;
	}
}
