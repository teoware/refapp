package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class Adapter {

	static DateTime parse(String dateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		return formatter.parseDateTime(dateTime);
	}

	static String print(DateTime date, String format) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		return formatter.print(date);
	}
}
