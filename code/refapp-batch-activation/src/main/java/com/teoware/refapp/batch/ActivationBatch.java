package com.teoware.refapp.batch;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.teoware.refapp.batch.job.UserActivationJob;

public class ActivationBatch extends Batch {

	@Inject
	private UserActivationJob userActivationJob;

	@PostConstruct
	@Override
	protected void setup() {
		addJob(userActivationJob);
	}
}
