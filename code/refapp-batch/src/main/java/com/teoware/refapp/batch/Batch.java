package com.teoware.refapp.batch;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.job.BatchJob;
import com.teoware.refapp.batch.util.BatchUtil;

public abstract class Batch {

	private static final Logger LOG = LoggerFactory.getLogger(Batch.class);

	private Map<Integer, BatchJob> jobs;

	@PostConstruct
	public abstract void setup();

	public void run() {
		if (jobs != null) {
			Set<Integer> priorities = jobs.keySet();

			if (priorities.size() > 0) {
				SortedSet<Integer> sortedPriorities = BatchUtil.createSortedSet(priorities);

				for (Integer priority : sortedPriorities) {
					runJob(priority);
				}
			} else {
				LOG.warn("Job list empty. No jobs will run");
			}
		} else {
			LOG.warn("Job list empty. No jobs will run");
		}
	}

	protected void addJob(Integer priority, BatchJob job) {
		if (job != null) {
			if (jobs == null) {
				jobs = new HashMap<Integer, BatchJob>();
			}

			if (jobs.get(priority) == null) {
				LOG.info("Adding new batch job {} with priority {}", job.getClass().getName(), priority);
				jobs.put(priority, job);
			} else {
				LOG.warn("Unable to add batch job. A job with priority {} already exists", priority);
			}
		}
	}

	protected void updateJob(Integer priority, BatchJob job) {
		if (jobs != null && job != null) {
			if (jobs.get(priority) == null) {
				LOG.warn("No job with priority {} exists. Adding new batch job {}", priority, job.getClass().getName());
			} else {
				LOG.info("Updating batch job {} with priority {}", job.getClass().getName(), priority);
			}
			jobs.put(priority, job);
		}
	}

	protected BatchJob removeJob(Integer priority) {
		if (jobs != null) {
			BatchJob job = jobs.remove(priority);
			LOG.info("Remoing batch job {} with priority {}", job.getClass().getName(), priority);
			return job;
		} else {
			LOG.warn("Job list is empty. Unable to remove batch job with priority {}", priority);
			return null;
		}
	}

	protected void runJob(Integer priority) {
		BatchJob job = jobs.get(priority);

		if (job != null) {
			LOG.info("Running batch job {} with priority {}", job.getClass().getName(), priority);

			job.run();
		} else {
			LOG.warn("No job with priority {} found", priority);
		}
	}
}
