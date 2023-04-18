package com.ianskenny.matrix;
// Additional author(s): Rahul Ramaka

public class Buffer {

	protected static final int MIN = -10000;
	protected static final int MAX = 10000;
	
	private final double[][][] buffer;
	private final int dim;
	
	public Buffer(int size, int dim) {
		this.dim = dim;
		buffer = new double[size][dim][dim];
		createIdentity();
//		createRandom();
//		createOnes();
	}
	
	public int getSize() {
		return buffer.length;
	}
	
	public int getDim() {
		return dim;
	}
	
	private void createRandom() {
		
		for (int i = 0; i < buffer.length; i++) {
			for (int j = 0; j < buffer[0].length; j++) {
				for (int k = 0; k < buffer[0].length; k++) {
					buffer[i][j][k] = MIN + Math.random() * (MAX - MIN);
				}
			}
		}
	}

	private void createIdentity() {
		
		for (int i = 0; i < buffer.length; i++) {
			for (int j = 0; j < buffer[0].length; j++) {
				for (int k = 0; k < buffer[0].length; k++) {
					buffer[i][j][k] = 0;
					if (j == k) {
						buffer[i][j][k] = 1;	
					}
				}
			}
		}
	}

	private void createOnes() {

		for (int i = 0; i < buffer.length; i++) {
			for (int j = 0; j < buffer[0].length; j++) {
				for (int k = 0; k < buffer[0].length; k++) {
					buffer[i][j][k] = 1;
				}
			}
		}
	}

	double[][] getMatrix(int i) {
		return buffer[i];
	}
}
