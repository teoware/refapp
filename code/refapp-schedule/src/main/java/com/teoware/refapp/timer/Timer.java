package com.teoware.refapp.timer;

import javax.annotation.Resource;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Timer {

	@Resource
	private TimerService timerService;

	private Timer() {
	}

	public javax.ejb.Timer createSingleActionTimer(DateTime dateTime) {
		return timerService.createSingleActionTimer(dateTime.getMillis(), new TimerConfig());
	}

	public javax.ejb.Timer createIntervalTimer(Interval interval) {
		return timerService.createIntervalTimer(interval.getStartMillis(), interval.getEndMillis(), new TimerConfig());
	}
}
