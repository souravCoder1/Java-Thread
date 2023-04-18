package com.ianskenny.threadedmean;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ThreadedMeanComputer {

	private static class TimingResult{
		public int nPartitions;
		public long[] times = new long[NUM_TESTS];
	}
	
	private static int INPUT_LIST_SIZE = 15_000_000;
	private static int NUM_TESTS = 10;
	private static int[] numPartitions = {1,2,4,8,16,32};
	
	public static void main (String[] args) throws InterruptedException {
		
		BigInteger[] data = new BigInteger[INPUT_LIST_SIZE];
		
		for (int i = 0; i < INPUT_LIST_SIZE; i++) {
			data[i] = new BigInteger(""+i);
		}
		
		DataSource ds = new DataSource(data.length);
		ds.fill(data);
		
		boolean noResult = false;
		
		Worker[] workers = null; 
		
		ArrayList<TimingResult> timingResults = new ArrayList<TimingResult>();

		// for each number of partitions
		for (int partCount = 0; partCount < numPartitions.length; partCount++) {

			// does not use a shared buffer
			BigInteger[][] partitions = ds.getPartitions(numPartitions[partCount]);
		
			// if we can't do any of the partitions (due to non-divisibility) we can't create a mean
			if (partitions == null || partitions.length == 0) {
				System.out.println("Unable to test with " + numPartitions[partCount] + " slices.");
				noResult = true; // can't compute mean
				continue;
			}
			
			TimingResult timingResult = new TimingResult();
						
			// for each test for this partition
			for (int testCount = 0; testCount < NUM_TESTS; testCount++) {
				
				Thread[] threads = new Thread[numPartitions[partCount]];
				workers = new Worker[numPartitions[partCount]];
		
				// get the start time for this run
				long startTime = System.nanoTime();
				
				// create the threads and runnables
				for (int i = 0; i < numPartitions[partCount]; i++) {
					workers[i] = new Worker(partitions[i]);
					threads[i] = new Thread(workers[i]);
					threads[i].start();			
				}
		
				// wait for all threads to finish
				for (int i = 0; i < numPartitions[partCount]; i++) {
					threads[i].join();
				}
				
				//get the end time for this run
				long endTime = System.nanoTime();
				
				// convert to ms
				long ms = TimeUnit.MILLISECONDS.convert((endTime-startTime), TimeUnit.NANOSECONDS);
				
				// store the timing info
				timingResult.nPartitions = numPartitions[partCount];
				timingResult.times[testCount] = ms;	
			}
			
			timingResults.add(timingResult);
		}

		// print out the timing results
		for (int i =0; i < timingResults.size(); i++) {
			
			System.out.println("Test " + i + " Partitions " + timingResults.get(i).nPartitions);
			
			float sumOfTimes = 0;
			float countOfTimes = timingResults.get(i).times.length;
			
			for (int j = 0; j < timingResults.get(i).times.length; j++) {
				sumOfTimes += timingResults.get(i).times[j];
				System.out.print(timingResults.get(i).times[j] + " ");
			}
			
			System.out.print(" Mean run time: " + sumOfTimes/countOfTimes + "\n");
		} 
		
		// This will only take the last mean result that was computed. That's fine
		if (noResult == false) {
			double sumForMean = 0;
			for (Worker w: workers) {
				sumForMean += w.getResult();
			}
			
			System.out.println("Mean of input list = " + Math.round(sumForMean/numPartitions[numPartitions.length-1]));
		}
	}
}
