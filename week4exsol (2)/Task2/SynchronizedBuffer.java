package com.ianskenny.syncmatrix;
// Additional author(s) : Rahul Ramaka


public class SynchronizedBuffer {

	protected static final int MIN = -2;
	protected static final int MAX = 2;
	
	private double[][][] buffer;
	private int dim;
	private int blockLength;

	private int nextBlockIndex;

	public SynchronizedBuffer(int size, int dim, int blockLength) {
		this.dim = dim;
		this.blockLength = blockLength;
		buffer = new double[size][dim][dim];
		createIdentity();
	}

	public int getNextBlockIndex() {
		return nextBlockIndex;
	}
	
	public synchronized int requestBlockIndex() {
		if (nextBlockIndex + blockLength > (buffer.length/2)) {
			return -1;
		}
		
		else {
			int ret = nextBlockIndex;
			nextBlockIndex += blockLength;
			return ret;
		}
	}
	
	public int getBlockLength() {
		return blockLength;
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

	public synchronized double[][] getMatrix(int i) {
		return buffer[i];
	}
}
