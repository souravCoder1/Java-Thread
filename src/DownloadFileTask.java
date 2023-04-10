import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DownloadFileTask implements Runnable {
    @Override
    public void run() {
        //      long sec = (long) (Math.random() * 100);

        //      Random random = new Random();
        //      long sec = random.nextLong(100);

        long sec = ThreadLocalRandom.current().nextLong();

        System.out.println("Downloading files...."+ Thread.currentThread().getName()); // 1

        try {
            System.out.println(sec);
            Thread.sleep(sec); // approx  // 2
        } catch (Exception e) {
            System.out.println("Exception!!!!!");
            e.printStackTrace();
        }

        System.out.println("Download complete: "+ Thread.currentThread().getName()); // 3
    }
}
