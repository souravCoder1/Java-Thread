import java.util.ArrayList;
import java.util.List;

public class ThreadDemo { // current is main
    void show(DownloadStatus downloadStatus) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) { // 10*100
            Thread thread = new Thread(new DownloadFileTask(downloadStatus));
            thread.start();
            threads.add(thread);
            //thread.join();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Total size " + downloadStatus.getTotalMb());
    }
}
