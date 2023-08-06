package com.ianskenny.matrix;
// Additional author(s): Rahul Ramaka

public class MatrixWorker implements Runnable {

	private final int startIndex;
	private final int numMatrices;
	private final double[][][] outputMatrices;
	private final int offset;
	private final Buffer buffer;
	
	public MatrixWorker(Buffer buffer, int si, int num) {
		startIndex = si;
		numMatrices = num;
		this.buffer = buffer;
		offset = buffer.getSize()/2;
		outputMatrices = new double[numMatrices][buffer.getDim()][buffer.getDim()];
	}

	public double[][][] getOutputMatrices() {
		return outputMatrices;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < numMatrices; i++) {
			double[][] mat1 = buffer.getMatrix(i + startIndex); // Matrix from first half of buffer
			double[][] mat2 = buffer.getMatrix(i + offset + startIndex); // Matrix from second half of buffer
			outputMatrices[i] = mult(mat1, mat2);
		}
	}

	// Matrix multiplication
	private double[][] mult(double[][] mat1, double[][] mat2) {
		double[][] prod = new double[mat1.length][mat1.length];
		
		  for (int i = 0; i < prod.length; i++) {
	            for (int j = 0; j < prod.length; j++) {
	                for (int k = 0; k < prod.length; k++)
	                    prod[i][j] += mat1[i][k] * mat2[k][j];
	            }
	        }

		  return prod;
	}

}
