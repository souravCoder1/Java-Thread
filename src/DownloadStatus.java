public class DownloadStatus {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private final Object lock3 = new Object();
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
    public void incrementTotalByte() {
        synchronized (lock1) {
            totalMb++; // 3 operation
        }
        totalMb++;
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }

    public void calculateTotalDownloadedFiles() {
        synchronized (lock2) {
            totalFiles++; // 3 operation
        }
    }
}
