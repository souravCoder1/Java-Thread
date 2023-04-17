public class DownloadStatus {

    private int totalMb = 0;

    public int getTotalMb() {
        return totalMb;
    }

    public void incrementTotalByte() { // shared resource
        totalMb++; // 3 operations
        // 1. clone main memory to thread memory
        // 2. update the value
        // 3. merge cpu to main memory
    }
}
