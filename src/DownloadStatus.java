import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {

    private int totalMb = 0;
    Lock lockResouce = new ReentrantLock();

    public int getTotalMb() {
        return totalMb;
    }

    public void incrementTotalByte() { // shared resource
        lockResouce.lock();
        try {
            totalMb++; // 3 operation
        } finally {
            lockResouce.unlock();
        }
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }
}
