package com.teoware.refapp.batch;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.teoware.refapp.batch.job.BatchJob;

public abstract class Batch {

	private Map<Integer, BatchJob> jobs;

	public void run() {
		if (jobs != null) {
			Set<Integer> priorities = jobs.keySet();

			if (priorities.size() > 0) {
				SortedSet<Integer> sortedPriorities = createSortedSet(priorities);

				for (Integer priority : sortedPriorities) {
					runJob(priority);
				}
			} else {
				// TODO Write warning to log
			}
		} else {
			// TODO Write warning to log
		}
	}

	protected void addJob(Integer priority, BatchJob job) {
		verifyJobMap();

		if (job != null) {
			if (jobs.get(priority) != null) {
				// TODO Write warning to log
			}

			jobs.put(priority, job);
		}
	}

	protected BatchJob removeJob(Integer priority) {
		verifyJobMap();

		return jobs.remove(priority);
	}

	protected void runJob(Integer priority) {
		verifyJobMap();

		BatchJob job = jobs.get(priority);

		if (job != null) {
			job.run();
		} else {
			// TODO Write warning to log
		}
	}

	private void verifyJobMap() {
		if (jobs == null) {
			jobs = new HashMap<Integer, BatchJob>();
		}
	}

	private SortedSet<Integer> createSortedSet(Set<Integer> set) {
		return new TreeSet<Integer>(set);
	}
}
