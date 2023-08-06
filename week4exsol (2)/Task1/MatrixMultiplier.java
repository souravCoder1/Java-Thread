package com.ianskenny.matrix;
// Additional author(s): Rahul Ramaka

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplier {

	protected static final int NUM_MATRICES = 1000;
	protected static final int DIM = 2;
	private static final int NUM_THREADS = 10;

	public static void main(String[] args) throws InterruptedException {
		
		long createStartTime = System.nanoTime();
		Buffer inputBuffer = new Buffer(NUM_MATRICES, DIM);
		long createEndTime = System.nanoTime();
		long cms = TimeUnit.MILLISECONDS.convert((createEndTime-createStartTime), TimeUnit.NANOSECONDS);		
		System.out.println("Matrix create time: " + cms + " ms");

		// Total number of tasks to be performed = total number of multiplications = NUM_MATRICES/2
		int nPerThread = (NUM_MATRICES/2)/NUM_THREADS;

		int startIndex = 0;

		MatrixWorker[] workers = new MatrixWorker[NUM_THREADS];
		Thread[] threads = new Thread[NUM_THREADS];
		
		System.out.println("Creating threads and workers.");
		
		for (int i = 0; i < NUM_THREADS; i++) {
			
			workers[i] = new MatrixWorker(inputBuffer, startIndex, nPerThread);
			
			threads[i] = new Thread(workers[i]);
			
			startIndex += nPerThread;
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

		// can get the results if needed.
		for (int i = 0; i < NUM_THREADS; i++) {
			// this loop would get the output of each thread
			// you could check the results here or do something else with them
			String output = Arrays.deepToString(workers[i].getOutputMatrices());
//			System.out.println(output);
		}
		
		System.out.println("Computing run time.");

		long ns = threadEndTime - threadStartTime;
		
		long ms = TimeUnit.MILLISECONDS.convert((ns), TimeUnit.NANOSECONDS);
		
		System.out.println("Run time (ns):" + ns);
		System.out.println("Run time (ms):" + ms);
	}
}
