package org.grizz.service;


public class Timer {
	private long tickTime;
	private long timeStarted;

	public Timer(long tickTime) {
		this.tickTime = tickTime;
	}

	public void start() {
		timeStarted = System.currentTimeMillis();
	}

	public boolean isRunning() {
		return (System.currentTimeMillis() - timeStarted) < tickTime;
	}

}
