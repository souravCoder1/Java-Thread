package com.ianskenny.computations;

import java.util.Random;

import com.ianskenny.memorymodel.Computation;
import com.ianskenny.memorymodel.Evaluator;
import com.ianskenny.memorymodel.Operation;

public class Producer implements Runnable {

	private static final int MAX_VAL = 100;
	private Random rand;
	private String[] operators = {"+","-","*","/"};
	private Buffer buffer;
	private boolean quit;

	public Producer(Buffer buffer) {
		rand = new Random();
		this.buffer = buffer;
		quit = false;
	}
	
	@Override
	public void run() {
		
		while (!quit) {
			buffer.add(randomComputation()); 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Computation randomComputation() {
		int op1 = rand.nextInt() % MAX_VAL;
		int op2 = rand.nextInt() % MAX_VAL;
		String oper = operators[Math.abs(rand.nextInt()%operators.length)];
		Operation op = new Operation(op1,op2,oper);
		Evaluator ev = new Evaluator(op);
		return new Computation(op, ev);		
	}
	
	public void setQuit() {
		this.quit = true;
	}
}
