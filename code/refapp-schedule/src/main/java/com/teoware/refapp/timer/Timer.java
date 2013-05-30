package com.teoware.refapp.timer;

import javax.annotation.Resource;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.util.time.DateTimeParser;

public class Timer {

	private static final Logger LOG = LoggerFactory.getLogger(Timer.class);

	@Resource
	private TimerService timerService;

	public javax.ejb.Timer createSingleActionTimer(DateTime dateTime) {
		return createSingleActionTimer(dateTime, new TimerConfig());
	}

	public javax.ejb.Timer createSingleActionTimer(DateTime dateTime, TimerConfig config) {
		LOG.info("Single action timer created with run time {}.", timestampString(dateTime.getMillis()));
		return timerService.createSingleActionTimer(dateTime.getMillis(), config);
	}

	public javax.ejb.Timer createIntervalTimer(Interval interval) {
		return createIntervalTimer(interval, new TimerConfig());
	}

	public javax.ejb.Timer createIntervalTimer(Interval interval, TimerConfig config) {
		LOG.info("Single action timer created with first run time {}, and next run time {}.",
				timestampString(interval.getStartMillis()), timestampString(interval.getEndMillis()));
		return timerService.createIntervalTimer(interval.getStartMillis(), interval.getEndMillis(), config);
	}

	private String timestampString(long millis) {
		return DateTimeParser.toString(new DateTime(millis), DateTimeParser.DATETIME_FULL_PATTERN);
	}
}
