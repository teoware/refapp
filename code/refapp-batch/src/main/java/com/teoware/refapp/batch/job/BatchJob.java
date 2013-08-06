package com.teoware.refapp.batch.job;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.task.BatchTask;
import com.teoware.refapp.batch.task.TaskResult;
import com.teoware.refapp.batch.task.TaskSetup;

public abstract class BatchJob {

	private static final Logger LOG = LoggerFactory.getLogger(BatchJob.class);

	private LinkedList<BatchTask> tasks = new LinkedList<BatchTask>();

	public String name() {
		return this.getClass().getSimpleName();
	}

	protected abstract void setup();

	public <R, S> void run() {
		if (tasks.size() > 0) {
			S data = null;
			for (BatchTask<R, S> task : tasks) {
				TaskResult<R> result = runTask(task, data);
				if (result != null) {
					if (result.terminate()) {
						LOG.warn("Task {} initiated terminate action. Batch job {} will now exit", task.name(), name());
						return;
					}
					data = (S) result.data();
				}
			}
		} else {
			throw new BatchException("Task list empty. No batch tasks will run");
		}
	}

	protected void addTask(BatchTask<?, ?> task) {
		if (task != null) {
			LOG.info("Adding new task {} to batch job {}", task.name(), name());
			tasks.add(task);
		} else {
			throw new BatchException("Unable to add batch task which is null");
		}
	}

	protected <R, S> TaskResult<R> runTask(BatchTask<R, S> task, S data) {
		if (task != null) {
			LOG.info("Batch job {} running task {}", name(), task.name());
			TaskSetup<S> setup = task.init();
			if (setup != null) {
				setup.init(data);
				task.setup(setup);
			}
			task.run();
			return task.result();
		} else {
			throw new BatchException("Unable to run batch task which is null");
		}
	}
}
