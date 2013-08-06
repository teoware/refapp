package com.teoware.refapp.batch.task;

public abstract class TaskSetup<T> {

	protected T data;

	public void init(T data) {
		this.data = data;
	}

	public T data() {
		return data;
	}
}
