package com.teoware.refapp.batch.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivatePendingUsersTask extends BatchTask {

	private static final Logger LOG = LoggerFactory.getLogger(ActivatePendingUsersTask.class);

	@Override
	public TaskSetup init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		LOG.info("Run called on task {}!", this.getClass().getName());
	}
}
