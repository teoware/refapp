package com.teoware.refapp.batch.schedule;

import javax.ejb.Schedule;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.ActivationBatch;
import com.teoware.refapp.util.time.DateTimeParser;

public class ActivationScheduler extends Scheduler {

	private static final Logger LOG = LoggerFactory.getLogger(ActivationScheduler.class);

	@Inject
	private ActivationBatch activationBatch;

	@Override
	@Schedule(hour = "*", minute = "*/5", second = "0")
	public void start() {
		LOG.info("Starting batch at {}", timestampString(DateTime.now().getMillis()));
		activationBatch.run();
	}

	private String timestampString(long millis) {
		return DateTimeParser.toString(new DateTime(millis), DateTimeParser.DATETIME_FULL_PATTERN);
	}
}
