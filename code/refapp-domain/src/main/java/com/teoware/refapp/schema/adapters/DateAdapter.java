package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateAdapter {

	private DateAdapter() {
	}

	public static DateTime parse(String date) throws Exception {
		DateTimeFormatter formatter = ISODateTimeFormat.date();
		return formatter.parseDateTime(date);
	}

	public static String print(DateTime date) throws Exception {
		DateTimeFormatter formatter = ISODateTimeFormat.date();
		return formatter.print(date);
	}
}
