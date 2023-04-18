package com.ianskenny.computations;

public class Main {

	private static final int BUFFER_SIZE = 10;
	private static final int NSLICES = 2;
	private static final int NUM_PRODUCERS = 1000;
	private static final int NUM_CONSUMERS = 1000;
	private static final int RUN_TIME = 30;
	
	public static void main(String[] args) throws InterruptedException {

		Buffer buffer = new Buffer(BUFFER_SIZE, NSLICES);
		Producer[] producers = new Producer[NUM_PRODUCERS];
		Consumer[] consumers = new Consumer[NUM_CONSUMERS];
		
		for (int i = 0; i < NUM_PRODUCERS;i++) {
			Producer prod = new Producer(buffer);
			producers[i] = prod;
			new Thread(prod,"prod_"+i).start();		
		}

		// This simply gives the producers a head start to prevent
		// consumers wasting a lot of time on a relatively empty buffer
		Thread.sleep(1000);

		for (int i = 0; i < NUM_CONSUMERS;i++) {
			Consumer cons = new Consumer(buffer);
			consumers[i] = cons;
			new Thread(cons, "cons_" +i).start();	
		}
		
		// the timing is indicative not exact. It allows comparison of 
		// various parameter changes without being a fully accurate
		// account of the thread run times.
		long startTime = System.currentTimeMillis();
		int t = 0;
		
		System.out.print("Working ");
		while (startTime + RUN_TIME * 1000 > System.currentTimeMillis()) {			
			if (++t%300 == 0) {
				System.out.print(".");
			}
			Thread.sleep(1);

		}
		System.out.println();
		
		int totalComps = 0;
		
		// get the number of computations processed by each consumer
		for (int i = 0; i < NUM_CONSUMERS; i++) {
			totalComps += consumers[i].getNumComputations();
			consumers[i].setQuit();
		}

		System.out.println("Total computations: " + totalComps);
		
		// kill the threads
		for (int i = 0; i < NUM_PRODUCERS; i++) {
			producers[i].setQuit();
		}
	}
}

