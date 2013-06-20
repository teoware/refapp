package com.teoware.refapp.schedule;

import javax.annotation.PostConstruct;
import javax.ejb.ScheduleExpression;
import javax.ejb.TimerConfig;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.timer.Timer;

public abstract class Scheduler {

	private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);

	@Inject
	private Timer timer;

	@PostConstruct
	public void timer() {
		try {
			timer.setScheduler(this);
			timer.setSchedule(schedule());
			timer.setConfig(config());
			timer.create();
		} catch (Exception e) {
			LOG.error("An error occured when trying to create timer", e);
			throw new SchedulerException("An error occured when trying to create timer", e);
		}
	}

	public String name() {
		return this.getClass().getSimpleName();
	}

	protected TimerConfig config() {
		return null;
	}

	protected abstract ScheduleExpression schedule();

	protected abstract Runme runnable();

	public void start() {
		try {
			Runme runnable = runnable();
			LOG.info("Sheduler {} starting runnable {}", name(), runnable.name());
			runnable.run();
		} catch (Exception e) {
			LOG.error("An error occured when starting runnable", e);
			throw new SchedulerException("An error occured when starting runnable", e);
		}
	}
}
