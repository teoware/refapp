package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class Adapter {

	static DateTime parse(String dateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		return formatter.parseDateTime(dateTime);
	}

	static String print(DateTime date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		return formatter.print(date);
	}
}
