package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class TimeAdapter {

	public static DateTime parse(String time) throws Exception {
		DateTimeFormatter formatter = ISODateTimeFormat.time();
		return formatter.parseDateTime(time);
	}

	public static String print(DateTime time) throws Exception {
		DateTimeFormatter formatter = ISODateTimeFormat.time();
		return formatter.print(time);
	}
}
