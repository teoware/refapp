package com.teoware.refapp.schedule;

public abstract class Runme {

	public String name() {
		return this.getClass().getSimpleName();
	}

	public abstract void run();
}
