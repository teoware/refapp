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
	private ScheduleExpression scheduleExpression;
	private TimerConfig config;
	private javax.ejb.Timer timer;

	@Resource
	private TimerService timerService;

	public void create() {
		if (verifyScheduler()) {
			LOG.info("Creating timer for scheduer {} with schedule {} {} {}", scheduler.name(),
					scheduleExpression.getHour(), scheduleExpression.getMinute(), scheduleExpression.getSecond());
			try {
				timer = timerService.createCalendarTimer(scheduleExpression, config);
			} catch (Exception e) {
				LOG.error("An error occured when trying to create timer", e);
				throw new TimerException("An error occured when trying to create timer", e);
			}
		} else {
			throw new TimerException("Scheduler not correctly configured. Unable to continue");
		}
	}

	public boolean verifyScheduler() {
		return scheduler != null && scheduleExpression != null;
	}

	@Timeout
	private void timout(javax.ejb.Timer timer) {
		scheduler.fire(timer);
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public ScheduleExpression getScheduleExpression() {
		return scheduleExpression;
	}

	public void setScheduleExpression(ScheduleExpression scheduleExpression) {
		this.scheduleExpression = scheduleExpression;
	}

	public TimerConfig getConfig() {
		return config;
	}

	public void setConfig(TimerConfig config) {
		this.config = config;
	}

	public javax.ejb.Timer getTimer() {
		return timer;
	}

	public void setTimer(javax.ejb.Timer timer) {
		this.timer = timer;
	}
}
