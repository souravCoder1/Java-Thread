import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {

    private int totalMb = 0;
    Lock lockResource = new ReentrantLock();

    public int getTotalMb() {
        return totalMb;
    }

    public void incrementTotalByte() { // shared resource
        synchronized (this) {
            totalMb++; // 3 operation
        }
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }
}
