package com.teoware.refapp.batch;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.teoware.refapp.batch.job.UserActivationJob;

public class ActivationBatch extends Batch {

	@Inject
	private UserActivationJob userActivationJob;

	@Override
	@PostConstruct
	public void setup() {
		addJob(1, userActivationJob);
	}
}
