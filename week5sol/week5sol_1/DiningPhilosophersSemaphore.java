package com.ianskenny.morethreads;

public class DiningPhilosophersSemaphore {
	private static final int SIZE = 5;
	private static ForkSemaphore[] forks = new ForkSemaphore[SIZE];
	private static PhilosopherSemaphore[] philosophers = new PhilosopherSemaphore[SIZE];
	
	public static void main(String[] args) {
		
		for (int i = 0; i < SIZE; i++) {
			forks[i] = new ForkSemaphore();
		}
		for (int i = 0; i < SIZE; i++) {
			philosophers[i] = new PhilosopherSemaphore(forks[i], forks[(i + 1) % forks.length], i+1);
		}
		for (int i = 0; i < SIZE; i++) {
			new Thread(philosophers[i]).start();
		}
	}
}
