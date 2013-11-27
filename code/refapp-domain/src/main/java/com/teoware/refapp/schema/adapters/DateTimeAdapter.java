package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;

public final class DateTimeAdapter {

	public static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	private DateTimeAdapter() {
	}

	public static DateTime parse(String date) {
		return Adapter.parse(date, FORMAT);
	}

	public static String print(DateTime date) {
		return Adapter.print(date, FORMAT);
	}
}
