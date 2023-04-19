import java.util.ArrayList;
import java.util.List;

public class ThreadDemo { // current is main
    void show(DownloadStatus downloadStatus) {

        Thread thread1 = new Thread(new DownloadFileTask(downloadStatus));
//       Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        Thread thread2 = new Thread(() -> {
            while (!downloadStatus.isDone()) {}
            System.out.println(downloadStatus.getTotalMb());
        });

        thread1.start();
        thread2.start();

    }
}
