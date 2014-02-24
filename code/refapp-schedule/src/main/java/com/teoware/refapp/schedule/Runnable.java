package com.teoware.refapp.schedule;

public abstract class Runnable {

    public String name() {
        return this.getClass().getSimpleName();
    }

    public abstract void run();
}
