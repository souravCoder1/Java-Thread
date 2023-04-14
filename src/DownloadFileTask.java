import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DownloadFileTask implements Runnable {

    DownloadStatus downloadStatus;

    public DownloadStatus getDownloadStatus() {
        return downloadStatus;
    }

    public DownloadFileTask(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    @Override
    public void run() {
        
        System.out.println("Downloading files...."+ Thread.currentThread().getName());

        for (int i = 0; i < 10000; i++) {
            downloadStatus.incrementTotalByte();
        }
        
        System.out.println("Download complete: "+ Thread.currentThread().getName()); 
    }
}
