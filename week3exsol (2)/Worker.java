package com.ianskenny.threadedmean;
import java.math.BigInteger;

public class Worker implements Runnable {

	private BigInteger[] data;
	private double result;
	
	public Worker(BigInteger[] d) {
		this.data = d;
	}
	
	public double getResult() {
		return result;
	}
	
	@Override
	public void run() {
	
		double sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i].floatValue();
		}
		
		result = sum/data.length;		
	}
}
