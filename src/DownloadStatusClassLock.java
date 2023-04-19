public class DownloadStatusClassLock {
    private int totalMb = 0;
    private int totalFiles = 0;

    public int getTotalFiles() {
        return totalFiles;
    }

    public int getTotalMb() {
        return totalMb;
    }

    // we have to make sure this method is executed only by a single thread at a time, throughput increases
    // synchronized block is better than method label, why??

    //
    public static synchronized void incrementTotalByte() { // shared resource
//        synchronized (DownloadStatusClassLock.class) {
//            totalMb++; // 3 operation
//        }
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }
    public void decrementTotalByte() { // shared resource
        synchronized (DownloadStatusClassLock.class) {
            totalMb--; // 3 operation
        }
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }

    public void showTotalFiles() { // shared resource
        synchronized (DownloadStatusClassLock.class) {
            totalFiles++; // 3 operation
        }
    }
}
