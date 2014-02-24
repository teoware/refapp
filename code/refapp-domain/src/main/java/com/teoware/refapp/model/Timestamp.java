package com.teoware.refapp.model;

import java.util.Calendar;

public class Timestamp {

    private Calendar timestamp;

    public Timestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public static Timestamp getInstance() {
        return new Timestamp(Calendar.getInstance());
    }
}
