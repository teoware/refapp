package com.teoware.refapp.batch.job;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.task.BatchTask;
import com.teoware.refapp.batch.task.TaskResult;
import com.teoware.refapp.batch.task.TaskSetup;
import com.teoware.refapp.batch.util.BatchUtil;

public abstract class BatchJob {

	private static final Logger LOG = LoggerFactory.getLogger(BatchJob.class);

	private Map<Integer, BatchTask> tasks;

	@PostConstruct
	public abstract void setup();

	public void run() {
		if (tasks != null) {
			Set<Integer> priorities = tasks.keySet();

			if (priorities.size() > 0) {
				SortedSet<Integer> sortedPriorities = BatchUtil.createSortedSet(priorities);

				TaskResult result = null;
				for (Integer priority : sortedPriorities) {
					result = runTask(priority, result);
					if (result != null && result.terminate()) {
						LOG.warn("Task initiated terminate action. Job will now exit");
						break;
					}
				}
			} else {
				LOG.warn("Task list empty. No tasks will run");
			}
		} else {
			LOG.warn("Task list empty. No tasks will run");
		}
	}

	protected void addTask(Integer priority, BatchTask task) {
		if (task != null) {
			if (tasks == null) {
				tasks = new HashMap<Integer, BatchTask>();
			}

			if (tasks.get(priority) == null) {
				LOG.info("Adding new batch task {} with priority {}", task.getClass().getName(), priority);
				tasks.put(priority, task);
			} else {
				LOG.warn("Unable to add batch task. A task with priority {} already exists", priority);
			}
		}
	}

	protected void updateJob(Integer priority, BatchTask task) {
		if (tasks != null && task != null) {
			if (tasks.get(priority) == null) {
				LOG.warn("No task with priority {} exists. Adding new batch task {}", priority, task.getClass()
						.getName());
			} else {
				LOG.info("Updating batch task {} with priority {}", task.getClass().getName(), priority);
			}
			tasks.put(priority, task);
		}
	}

	protected BatchTask removeTask(Integer priority) {
		if (tasks != null) {
			BatchTask task = tasks.remove(priority);
			LOG.info("Remoing batch task {} with priority {}", task.getClass().getName(), priority);
			return task;
		} else {
			LOG.warn("Task list is empty. Unable to remove batch task with priority {}", priority);
			return null;
		}
	}

	protected TaskResult runTask(Integer priority, TaskResult previousResult) {
		BatchTask task = tasks.get(priority);

		if (task != null) {
			LOG.info("Running batch task {} with priority {}", task.getClass().getName(), priority);

			TaskSetup setup = task.createSetup();
			setup.setPreviousResult(previousResult);
			task.setup(setup);
			task.run();
			return task.result();
		} else {
			LOG.warn("No task with priority {} found", priority);
			return null;
		}
	}
}
