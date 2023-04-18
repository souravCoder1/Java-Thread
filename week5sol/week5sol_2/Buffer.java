package com.ianskenny.computations;

import java.util.Random;

import com.ianskenny.memorymodel.Computation;

public class Buffer {

	private Computation[] buffer;
	private BufferSemaphore[] semaphores;
	private Random rand;
	
	public Buffer(int size, int nslices) {
		buffer = new Computation[size];
		semaphores = new BufferSemaphore[nslices];
		rand = new Random();
		int offset = 0;
		for (int i = 0; i < nslices; i++) {
			semaphores[i] = new BufferSemaphore(offset, (offset + (size/nslices)-1));
			offset += (size/nslices);
		}
	}
	 
	public boolean add(Computation c) {
		for (int i = 0; i < semaphores.length; i++) {
			// this simply selects random slices until one can be accessed
			// or nslices is reached. This doesn't need to be up to nslices
			// it could be some other value.
			int slice = Math.abs(rand.nextInt() % semaphores.length);
			if (semaphores[slice].take()) {
				if (!bufferAdd(c,semaphores[slice])) {
					semaphores[slice].release();					
					continue;
				}
				else {
					semaphores[slice].release();
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean bufferAdd(Computation c, BufferSemaphore sem) {
		for (int i = sem.getStart(); i<=sem.getEnd();i++) {
			if (buffer[i] == null) {
				buffer[i] = c;
				c.setBufferIndex(i);
				return true;
			}
		}
		return false;
	}

	public Computation take() {
	
		for (int i = 0; i < semaphores.length; i++) {
			int slice = Math.abs(rand.nextInt() % semaphores.length);
			if (semaphores[slice].take()) {
				Computation comp = bufferTake(semaphores[slice]);
				if (comp == null) {
					semaphores[slice].release();					
					continue;
				}
				else {
					semaphores[slice].release();
					buffer[comp.getBufferIndex()] = null;
					return comp;
				}
			}
		}		
		return null;
	}

	private Computation bufferTake(BufferSemaphore sem) {
		for (int i = sem.getStart(); i < sem.getEnd(); i++) {
			if (buffer[i] != null) {
				return buffer[i];
			}
		}
		return null;
	}
	
	// optional method to print out the state of the buffer.
	public String stats() {
		
		String s = "";
		
		for (int i = 0; i < semaphores.length; i++) {
			s += "Slice " + i + "\n";
			int full = 0;
			int empty = 0;
			for (int j = semaphores[i].getStart(); j <= semaphores[i].getEnd(); j++) {
				if (buffer[j] == null) {
					empty++;
				}
				else {
					full++;
				}
			}
			
			s += "Full " + full + " Empty " + empty + "\n";			
		}
		
		for (int i = 0; i < buffer.length; i++) {
			if(buffer[i] != null) {
				s += i + " " + buffer[i].getOperation().toString() + " ";
			}
			else s += " null ";
		}
		
		s += "\n";

		return s;
	}
}
