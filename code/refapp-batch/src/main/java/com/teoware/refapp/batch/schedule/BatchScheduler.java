package com.teoware.refapp.batch.schedule;

import javax.ejb.ScheduleExpression;

import com.teoware.refapp.batch.Batch;
import com.teoware.refapp.schedule.Runnable;
import com.teoware.refapp.schedule.Scheduler;

public abstract class BatchScheduler extends Scheduler {

	private Batch batch;
	private Schedule schedule;

	protected abstract void setup();

	protected void setup(Batch batch, Schedule schedule) {
		this.batch = batch;
		this.schedule = schedule;
	}

	@Override
	protected ScheduleExpression schedule() {
		ScheduleExpression scheduleExpression = new ScheduleExpression();
		scheduleExpression.hour(schedule.getHour());
		scheduleExpression.minute(schedule.getMinute());
		scheduleExpression.second(schedule.getSecond());
		return scheduleExpression;
	}

	@Override
	protected Runnable runnable() {
		return batch;
	}
}
