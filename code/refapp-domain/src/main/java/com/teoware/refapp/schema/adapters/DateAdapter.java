package com.teoware.refapp.schema.adapters;

import org.joda.time.DateTime;

public final class DateAdapter {

    public static final String PATTERN = "yyyy-MM-dd";

    private DateAdapter() {
    }

    public static DateTime parse(String date) {
        return Adapter.parse(date, PATTERN);
    }

    public static String print(DateTime date) {
        return Adapter.print(date, PATTERN);
    }
}
