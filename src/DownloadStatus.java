import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {
    private int totalMb = 0;
    private int totalFiles = 0;

    public int getTotalFiles() {
        return totalFiles;
    }

    public int getTotalMb() {
        return totalMb;
    }

    // we have to make sure this method is executed only by a single thread at a time, throughput decreases 
    // rule of thumb: synchronized block is better than method label, why?? use synchronized block when 100% necessary

    // time slicing algorithm
    public synchronized void incrementTotalByte() { // shared resource
//        synchronized (this) {
//            totalMb++; // 3 operation
//        }
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }
    public void decrementTotalByte() { // shared resource
        synchronized (this) {
            totalMb--; // 3 operation
        }
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }

    public void showTotalFiles() { // shared resource
        synchronized (this) {
            totalFiles++; // 3 operation
        }
    }
}
