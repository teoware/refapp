package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;

public final class TimeAdapter {

    public static final String PATTERN = "HH:mm:ss.SSS";

    private TimeAdapter() {
    }

    public static DateTime parse(String date) {
        return Adapter.parse(date, PATTERN);
    }

    public static String print(DateTime date) {
        return Adapter.print(date, PATTERN);
    }
}
