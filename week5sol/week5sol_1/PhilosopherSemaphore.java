package com.ianskenny.morethreads;

public class PhilosopherSemaphore implements Runnable {

	private int number;
	private ForkSemaphore fork1;
	private ForkSemaphore fork2;
	
	public PhilosopherSemaphore(ForkSemaphore f1, ForkSemaphore f2, int n) {
		number = n;
		fork1 = f1;
		fork2 = f2;
	}
	
	public void run() {
		while (true) {
			act("thinking");	
			if (fork1.take()) {
				if (fork2.take()) {
					act("eating");
					fork1.putDown();
					fork2.putDown();
				}
				else {
					fork1.putDown();
				}
			}
		}
	}
	
	private void act(String s) {
		announce ("is " + s);
		try {
			Thread.sleep((long)(50 + Math.random() * 300));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void announce(String s) {
		System.out.println("Philosopher " + number + " " + s);
	}
}
