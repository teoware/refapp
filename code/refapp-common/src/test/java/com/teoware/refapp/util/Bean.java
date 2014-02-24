package com.teoware.refapp.util;

public class Bean {

    private String myString;
    private Long myLong;
    private int myInt;

    public Bean() {
    }

    public Bean(String myString, Long myLong, int myInt) {
        this.myString = myString;
        this.myLong = myLong;
        this.myInt = myInt;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public Long getMyLong() {
        return myLong;
    }

    public void setMyLong(Long myLong) {
        this.myLong = myLong;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }
}
