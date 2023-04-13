public class DownloadStatus {

    private int totalMb = 0;

    public int getTotalMb() {
        return totalMb;
    }

    public void incrementTotalByte() { // shared resource
        totalMb++;
    }
}
