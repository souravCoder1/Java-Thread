package com.ianskenny.computations;

import java.util.concurrent.Semaphore;

public class BufferSemaphore {

	private Semaphore semaphore;
	private int start;
	private int end;
	
	public BufferSemaphore(int start, int end) {
		semaphore = new Semaphore(1);
		this.start = start;
		this.end = end;
	}
	
	public boolean take() {
		return semaphore.tryAcquire();
	}
	
	public void release() {
		semaphore.release();
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
}
