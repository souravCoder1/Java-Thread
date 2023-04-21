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
            while (!downloadStatus.isDone()) {
                synchronized (downloadStatus) {  // IllegalMonitorStateException – if the current thread is not the owner of the object's monitor
                    //System.out.println(1);
                    try {
                        downloadStatus.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            System.out.println(downloadStatus.getTotalMb());
        });

        thread1.start(); // note: Thread 1 had to flag 'done' true for thread 2 to finish execution and print totalMb. Thread 2 did not
        // wait for thread 1 to complete and due to done being flagged as false the loop inside thread 2 runs indefinitely.
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        thread2.start();

    }
}
