package com.teoware.refapp.batch.schedule;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.Batch;

public abstract class Scheduler {

	private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void timer() {
		TimerConfig config = config();
		ScheduleExpression schedule = schedule();

		if (schedule == null) {
			LOG.error("Shedule is not set");
		}

		LOG.info("Creating timer {} with schedule {} {} {}", name(), schedule.getHour(), schedule.getMinute(),
				schedule.getSecond());
		timerService.createCalendarTimer(schedule, config);
	}

	public String name() {
		return this.getClass().getSimpleName();
	}

	protected TimerConfig config() {
		return null;
	}

	protected abstract ScheduleExpression schedule();

	protected abstract Batch init();

	@Timeout
	protected void start() {
		Batch batch = init();
		LOG.info("Timer {} starting batch job {}", name(), batch.name());
		batch.run();
	}
}
