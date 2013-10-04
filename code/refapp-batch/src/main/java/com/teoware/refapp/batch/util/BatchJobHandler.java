package com.teoware.refapp.batch.util;

import java.util.LinkedList;

import com.teoware.refapp.batch.BatchException;
import com.teoware.refapp.batch.job.BatchJob;

public class BatchJobHandler {

	private LinkedList<BatchJob> jobs = new LinkedList<BatchJob>();

	public int count() {
		return jobs.size();
	}

	public LinkedList<BatchJob> list() {
		return jobs;
	}

	public void add(BatchJob job) {
		if (job != null) {
			jobs.add(job);
		} else {
			throw new BatchException("Unable to add a batch job which is null");
		}
	}

	public void remove(BatchJob job) {
		if (job != null) {
			jobs.remove(job);
		} else {
			throw new BatchException("Unable to remove a batch task which is null");
		}
	}
}
