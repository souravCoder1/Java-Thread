package com.ianskenny.morethreads;
import java.util.concurrent.Semaphore;

public class ForkSemaphore {
	public Semaphore fork = new Semaphore(1);

	public ForkSemaphore() {}
	public boolean take() {
	    return fork.tryAcquire();
	}
	public void putDown() {
	    fork.release();
	}
}
