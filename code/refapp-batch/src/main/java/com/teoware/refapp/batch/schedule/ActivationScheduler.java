package com.teoware.refapp.batch.schedule;

import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.teoware.refapp.batch.ActivationBatch;
import com.teoware.refapp.schedule.Runme;
import com.teoware.refapp.schedule.Scheduler;

@Startup
@Singleton
public class ActivationScheduler extends Scheduler {

	@Inject
	private ActivationBatch activationBatch;

	@Override
	protected ScheduleExpression schedule() {
		ScheduleExpression schedule = new ScheduleExpression();
		return schedule.hour("*").minute("*/5").second("0");
	}

	@Override
	protected Runme runnable() {
		return activationBatch;
	}
}
