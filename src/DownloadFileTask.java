import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DownloadFileTask implements Runnable {

    DownloadStatus downloadStatus;
    public DownloadFileTask(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    @Override
    public void run() {
        
        System.out.println("Downloading files...."+ Thread.currentThread().getName());

        for (int i = 0; i < 1000000; i++) {
            downloadStatus.incrementTotalByte();
        }

        downloadStatus.done();

        synchronized (downloadStatus) {
            downloadStatus.notify();
        }
        System.out.println("Download complete: "+ Thread.currentThread().getName()); 
    }
}
