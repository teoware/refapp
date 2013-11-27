package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateTimeAdapter {

	private DateTimeAdapter() {
	}

	public static DateTime parse(String dateTime) throws Exception {
		DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
		return formatter.parseDateTime(dateTime);
	}

	public static String print(DateTime dateTime) throws Exception {
		DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
		return formatter.print(dateTime);
	}
}
