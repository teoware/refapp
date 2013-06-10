package com.teoware.refapp.batch.job;

import java.util.ArrayDeque;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.task.BatchTask;
import com.teoware.refapp.batch.task.TaskResult;
import com.teoware.refapp.batch.task.TaskSetup;

public abstract class BatchJob {

	private static final Logger LOG = LoggerFactory.getLogger(BatchJob.class);

	private Queue<BatchTask> tasks = new ArrayDeque<BatchTask>();

	public String name() {
		return this.getClass().getSimpleName();
	}

	protected abstract void setup();

	public void run() {
		if (tasks.size() > 0) {
			TaskResult result = null;
			for (BatchTask task : tasks) {
				result = runTask(task, result);
				if (terminateJob(result)) {
					LOG.warn("Task {} initiated terminate action. Batch job {} will now exit", task.name(), name());
					break;
				}
			}
		} else {
			LOG.warn("Task list empty. No batch tasks will run");
		}
	}

	protected void addTask(BatchTask task) {
		if (task != null) {
			LOG.info("Adding new task {} to batch job {}", task.name(), name());
			tasks.add(task);
		} else {
			LOG.warn("Unable to add batch task which is null");
		}
	}

	protected TaskResult runTask(BatchTask task, TaskResult previousResult) {
		if (task != null) {
			LOG.info("Batch job {} running task {}", name(), task.name());

			TaskSetup setup = task.init();
			if (setup != null) {
				setup.setPreviousResult(previousResult);
				task.setup(setup);
			}
			task.run();
			return task.result();
		} else {
			LOG.warn("Unable to run batch task which is null");
			return null;
		}
	}

	private boolean terminateJob(TaskResult result) {
		return result != null && result.terminate();
	}
}
