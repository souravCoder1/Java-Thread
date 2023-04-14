import java.util.ArrayList;
import java.util.List;

public class ThreadDemo { // current is main
    void show() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        List<Thread> threads = new ArrayList<>();
        List<DownloadFileTask> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) { // 10*100
            DownloadStatus downloadStatus = new DownloadStatus();

            //DownloadFileTask downloadFileTask = new DownloadFileTask();
            //downloadFileTask.setDownloadStatus(downloadStatus);

            DownloadFileTask downloadFileTask = new DownloadFileTask(downloadStatus);
            Thread thread = new Thread(downloadFileTask);
            thread.start();
            threads.add(thread);
            tasks.add(downloadFileTask);
            //thread.join();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int totalMB = tasks.stream()
                        .map(t -> t.getDownloadStatus().getTotalMb())
                                //.reduce(0, (a,b) -> a+b);
                                        .reduce(Integer::sum).get();


        System.out.println("Total size " + totalMB);
    }
}
