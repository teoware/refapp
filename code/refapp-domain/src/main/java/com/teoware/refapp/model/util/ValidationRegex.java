package com.teoware.refapp.model.util;

public interface ValidationRegex {

    public static final String USERNAME = "[\\w\\-\\.]+";
    public static final String TITLE = ".+";
    public static final String UUID = "[0-9a-fA-F-]{36}";
}
