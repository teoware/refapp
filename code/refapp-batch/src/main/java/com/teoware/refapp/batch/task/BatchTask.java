package com.teoware.refapp.batch.task;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.domain.TaskResult;
import com.teoware.refapp.batch.domain.TaskSetup;

public abstract class BatchTask<T> {

	public String name() {
		return this.getClass().getSimpleName();
	}

	@SuppressWarnings("unchecked")
	protected T convert(Object data) {
		try {
			return (T) data;
		} catch (ClassCastException e) {
			throw new BatchException("Incorrect data type", e);
		}
	}

	public abstract TaskSetup setup(Object data);

	public abstract TaskResult run(TaskSetup setup);
}
