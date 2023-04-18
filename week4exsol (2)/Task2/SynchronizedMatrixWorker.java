package com.ianskenny.syncmatrix;
// Additional author(s) : Rahul Ramaka


public class SynchronizedMatrixWorker implements Runnable {

	private int matrixIndex;
	private double[][][] output;
	private int offset;
	private SynchronizedBuffer buffer;
	
	public SynchronizedMatrixWorker(SynchronizedBuffer buffer) {
		this.buffer = buffer;
		offset = buffer.getSize()/2;
		output = new double[buffer.getSize()/2][buffer.getDim()][buffer.getDim()];
	}

	public double[][][] getOutputMatrices() {
		return output;
	}
	
	@Override
	public void run() {

		String name = Thread.currentThread().getName();

		while (true) {

//			System.out.println(name + ": requesting block.");
			int nextBlockIndex = buffer.requestBlockIndex();
//			System.out.println(name + ": got block: "+ nextBlockIndex);
			
			if (nextBlockIndex < 0) {
				System.out.println(name + ": no blocks left.");
				return; // no blocks left
			}
			
			//System.out.println(": processing block.");
			for (int i = 0; i < buffer.getBlockLength(); i++) {
				double[][] mat1 = buffer.getMatrix(i + nextBlockIndex); // Get matrix from first half
				double[][] mat2 = buffer.getMatrix(i + nextBlockIndex + offset); // Get matrix from second half
				output[i] = mult(mat1, mat2);
			}

			matrixIndex += buffer.getBlockLength();
			if (matrixIndex + buffer.getBlockLength() > output.length) {
				System.out.println(name + ": thread buffer full.");			
				return; // this thread's buffer is full
			}
				
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

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
