public class DownloadStatus {
    private boolean isDone = false;
    private int totalMb = 0;

    public boolean isDone() {
        synchronized (this) {
            return isDone;
        }
    }

    public void done() {
        isDone = true;
    }

    public void setTotalMb(int totalMb) {
        this.totalMb = totalMb;
    }

    public int getTotalMb() {
        return totalMb;
    }

    public void incrementTotalByte() { // shared resource

        synchronized (this) {
            totalMb++;
        }// 3 operations
        // 1. clone main memory to thread local memory
        // 2. update the value
        // 3. merge cpu to main memory
    }
}
