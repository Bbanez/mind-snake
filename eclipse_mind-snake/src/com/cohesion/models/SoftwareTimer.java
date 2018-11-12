package com.cohesion.models;

public class SoftwareTimer {

	private long cTimer, cTime;
	private boolean timerFlag = false;
	private long passedTime;

	public SoftwareTimer() {
	}

	public void start(int timee) {
		if (!timerFlag) {
			timerFlag = true;
			cTime = timee;
			cTimer = System.currentTimeMillis();
		}
	}

	public boolean isStarted() {
		return timerFlag;
	}

	public boolean check() {
		if (timerFlag) {
			passedTime = System.currentTimeMillis() - (cTimer + cTime);
			if (passedTime >= 0) {
				return true;
			}
		}
		return false;
	}

	public void stop() {
		if (timerFlag) {
			timerFlag = false;
		}
	}

	public long getPassedTime() {
		passedTime = System.currentTimeMillis() - cTimer;
		return passedTime;
	}
}
