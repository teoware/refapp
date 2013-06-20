package com.teoware.refapp.batch;

import java.util.ArrayDeque;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.job.BatchJob;
import com.teoware.refapp.schedule.Runme;

public abstract class Batch extends Runme {

	private static final Logger LOG = LoggerFactory.getLogger(Batch.class);

	private final Queue<BatchJob> jobs = new ArrayDeque<BatchJob>();

	public String name() {
		return this.getClass().getSimpleName();
	}

	protected abstract void setup();

	@Override
	public void run() {
		if (jobs.size() > 0) {
			for (BatchJob job : jobs) {
				run(job);
			}
		} else {
			LOG.warn("Job list empty. No jobs will run");
		}
	}

	protected void add(BatchJob job) {
		if (job != null) {
			LOG.info("Adding new job {} to batch {}", job.name(), name());
			jobs.add(job);

		} else {
			LOG.warn("Unable to add batch job which is null");
		}
	}

	protected void run(BatchJob job) {
		if (job != null) {
			LOG.info("Batch {} running job {}", name(), job.name());
			job.run();
		} else {
			LOG.warn("Unable to run batch job which is null");
		}
	}
}
