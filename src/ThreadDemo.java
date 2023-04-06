public class ThreadDemo {
    void show() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DownloadFileTask());
            //System.out.println(Thread.currentThread().getName() + i);
            thread.start(); // ==> run
        }
//        Thread thread1 = new Thread(new DownloadFileTask());
//        Thread thread2 = new Thread(new DownloadFileTask());
//        Thread thread3 = new Thread(new DownloadFileTask());
//        Thread thread4 = new Thread(new DownloadFileTask());
    }
}
