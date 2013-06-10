package com.teoware.refapp.timer;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.schedule.Scheduler;

@Singleton
public class Timer {

	private static final Logger LOG = LoggerFactory.getLogger(Timer.class);

	private Scheduler scheduler;
	private TimerConfig config;
	private ScheduleExpression schedule;
	private javax.ejb.Timer timer;

	@Resource
	private TimerService timerService;

	public void create() {
		verify();

		LOG.info("Creating timer {} with schedule {} {} {}", scheduler.name(), schedule.getHour(),
				schedule.getMinute(), schedule.getSecond());
		try {
			timer = timerService.createCalendarTimer(schedule, config);
		} catch (Exception e) {
			LOG.error("An error occured when trying to create timer", e);
			throw new TimerException("An error occured when trying to create timer", e);
		}
	}

	public void verify() {
		if (scheduler == null) {
			LOG.error("Unable to create timer that has no owning scheduler");
			throw new TimerException("Unable to create timer that has no owning scheduler");
		}
		if (schedule != null) {
			LOG.error("Unable to create timer that has no schedule");
			throw new TimerException("Unable to create timer that has no schedule");
		}
	}

	@Timeout
	private void start() {
		scheduler.start();
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public TimerConfig getConfig() {
		return config;
	}

	public void setConfig(TimerConfig config) {
		this.config = config;
	}

	public ScheduleExpression getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleExpression schedule) {
		this.schedule = schedule;
	}

	public javax.ejb.Timer getTimer() {
		return timer;
	}

	public void setTimer(javax.ejb.Timer timer) {
		this.timer = timer;
	}
}
