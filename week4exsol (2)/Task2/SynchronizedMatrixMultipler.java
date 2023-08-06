package com.ianskenny.syncmatrix;
// Additional author(s) : Rahul Ramaka

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SynchronizedMatrixMultipler {
	
	protected static final int NUM_MATRICES = 2000000;
	protected static final int DIM = 4;
	protected static final int BLOCK_LENGTH = 20000;
	private static final int NUM_THREADS = 1;
	
	private static SynchronizedBuffer buffer;
	
	public static void main(String[] args) throws InterruptedException {
		
		long createStartTime = System.nanoTime();
		buffer = new SynchronizedBuffer(NUM_MATRICES,DIM,BLOCK_LENGTH);
		long createEndTime = System.nanoTime();
		long cms = TimeUnit.MILLISECONDS.convert((createEndTime-createStartTime), TimeUnit.NANOSECONDS);		
		System.out.println("Matrix create time: " + cms);

		SynchronizedMatrixWorker[] workers = new SynchronizedMatrixWorker[NUM_THREADS];
		Thread[] threads = new Thread[NUM_THREADS];
		
		System.out.println("Creating threads and workers.");
		
		for (int i = 0; i < NUM_THREADS; i++) {
			
			workers[i] = new SynchronizedMatrixWorker(buffer);
			
			threads[i] = new Thread(workers[i]);
		}

		long threadStartTime = System.nanoTime();

		System.out.println("Starting threads.");

		for (int i = 0; i < NUM_THREADS; i++) {
			threads[i].start();
		}

		System.out.println("Waiting for threads.");

		for (int i = 0; i < NUM_THREADS; i++) {
			threads[i].join();
		}

		long threadEndTime = System.nanoTime();

		System.out.println("Final block index: " + buffer.getNextBlockIndex());
		
		System.out.println("Computing run time.");

		long ns = threadEndTime - threadStartTime;
		
		long ms = TimeUnit.MILLISECONDS.convert((ns), TimeUnit.NANOSECONDS);
		
		System.out.println("Run time (ns):" + ns);
		System.out.println("Run time (ms):" + ms);

		// Can get the results if needed for debugging
		for (int i = 0; i < NUM_THREADS; i++) {
			// this loop would get the output of each thread
			// you could check the results here or do something else with them
			String output = Arrays.deepToString(workers[i].getOutputMatrices());
//			System.out.println(output);
		}
		
	}
}
