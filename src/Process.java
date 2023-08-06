// inter-thread communication for same intrinsic lock (aka monitor lock)
public class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce method….");
            wait();
            System.out.println("Again in the produce method….");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            System.out.println("Consume method is executed…");
            notify();
            // It is not going to release the lock we can make further operations
            System.out.println("Still Executing…");
        }
    }
}
