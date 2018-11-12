package com.cohesion.threads;

import java.io.IOException;

public class ConsoleInput extends Thread {

	private boolean dataInBuffer = false;
	private String input = "";
	private int i;

	private boolean stopThread = false;
	private boolean isRunning = false;

	public ConsoleInput() {
		i = 0;
	}

	public void run() {

		isRunning = true;

		while (!stopThread) {
			try {
				i = 0;
				while (i != 13 && i != 10) {
					i = System.in.read();
					if (i != 13 && i != 10) {
						input += (char) i;
					}
				}
				dataInBuffer = true;
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		isRunning = false;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void stopThread() {
		stopThread = true;
	}

	public boolean available() {
		return dataInBuffer;
	}

	public String read() {
		String temp = input;
//		if(!input.equals(""))	{
//			System.out.println("Input: " + input);
//		}
		input = "";
		dataInBuffer = false;
		i = 0;
		return temp;

	}
}
