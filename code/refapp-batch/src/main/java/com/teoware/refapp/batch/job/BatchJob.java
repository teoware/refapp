package com.teoware.refapp.batch.job;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.teoware.refapp.batch.task.BatchTask;
import com.teoware.refapp.batch.task.TaskResult;
import com.teoware.refapp.batch.task.TaskSetup;

public abstract class BatchJob {

	private Map<Integer, BatchTask> tasks;

	public void run() {
		if (tasks != null) {
			Set<Integer> priorities = tasks.keySet();

			if (priorities.size() > 0) {
				SortedSet<Integer> sortedPriorities = createSortedSet(priorities);

				TaskResult result = null;
				for (Integer priority : sortedPriorities) {
					BatchTask task = tasks.get(priority);

					TaskSetup setup = task.createSetup();
					setup.setPreviousResult(result);
					task.setup(setup);
					task.run();
					result = task.result();

					if (result.terminate()) {
						// TODO Write warning to log
						break;
					}
				}
			} else {
				// TODO Write warning to log
			}
		} else {
			// TODO Write warning to log
		}
	}

	protected void addTask(Integer priority, BatchTask task) {
		verifyTaskMap();

		if (task != null) {
			if (tasks.get(priority) != null) {
				// TODO Write warning to log
			}

			tasks.put(priority, task);
		}
	}

	protected BatchTask removeTask(Integer priority) {
		verifyTaskMap();

		return tasks.remove(priority);
	}

	private void verifyTaskMap() {
		if (tasks == null) {
			tasks = new HashMap<Integer, BatchTask>();
		}
	}

	private SortedSet<Integer> createSortedSet(Set<Integer> set) {
		return new TreeSet<Integer>(set);
	}
}
