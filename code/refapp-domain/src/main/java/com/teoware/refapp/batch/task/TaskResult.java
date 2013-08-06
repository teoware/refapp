package com.teoware.refapp.batch.task;

import java.lang.reflect.ParameterizedType;

public abstract class TaskResult<T> {

	protected T data;
	protected Boolean terminate;

	protected TaskResult(T data, Boolean terminate) {
		this.data = data;
		this.terminate = terminate;
	}

	public T data() {
		return data;
	}

	public boolean terminate() {
		return terminate;
	}

	public Class<?> dataClass() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<?>) parameterizedType.getActualTypeArguments()[0];
	}
}
