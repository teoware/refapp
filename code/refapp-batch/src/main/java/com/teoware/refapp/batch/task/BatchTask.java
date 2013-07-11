package com.teoware.refapp.batch.task;

public abstract class BatchTask<R, S> {

	protected TaskSetup<S> setup;
	protected TaskResult<R> result;

	public String name() {
		return this.getClass().getSimpleName();
	}

	public abstract TaskSetup<S> init();

	public void setup(TaskSetup<S> setup) {
		this.setup = setup;
	}

	public abstract void run();

	public TaskResult<R> result() {
		return result;
	}
}
