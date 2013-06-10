package com.teoware.refapp.batch.task;

public abstract class BatchTask {

	protected TaskSetup setup;
	protected TaskResult result;

	public String name() {
		return this.getClass().getSimpleName();
	}

	public abstract TaskSetup init();

	public void setup(TaskSetup setup) {
		this.setup = setup;
	}

	public abstract void run();

	public TaskResult result() {
		return result;
	}
}
