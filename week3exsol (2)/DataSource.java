package com.ianskenny.threadedmean;
import java.math.BigInteger;

public class DataSource {

	private BigInteger[] data;
	
	public DataSource(int size) {
		data = new BigInteger[size];
	}

	public void fill(BigInteger[] indata) {
		
		if (this.data.length != indata.length) {
			return;
		}
		
		for (int i = 0; i < this.data.length; i++) {
			data[i] = indata[i];
		}
	}
	
	public BigInteger[][] getPartitions(int nPartitions) {
		
		// must be divisible without remainder
		if (data.length % nPartitions != 0) {
			return null;
		}
		
		int partSize = data.length / nPartitions;

		BigInteger[][] results = new BigInteger[nPartitions][];
		
		int j = 0;
		
		for (int i = 0; i < nPartitions; i++) {
		
			results[i] = new BigInteger[partSize];
			
			for (int k = 0; k < partSize; k++) {
				results[i][k] = this.data[j];
				j++;
			}
		}
		
		return results;
	}
}
