package com.ianskenny.computations;

import com.ianskenny.memorymodel.Computation;

public class Consumer implements Runnable {

	private Buffer buffer;
	private int numComps;
	private boolean quit;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
		quit = false;
	}
	
	@Override
	public void run() {

		while (!quit) {
			
			Computation comp = buffer.take();
			
			if (comp != null) {
				// the result can be discarded here because we just want to 
				// know how many computations are done.
				float result = comp.getEvaluator().evaluate(); 
				numComps++;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getNumComputations() {
		return numComps;
	}
	
	public void setQuit() {
		this.quit = true;
	}
}
