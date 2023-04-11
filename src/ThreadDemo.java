public class ThreadDemo { // current is main
    void show() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        Thread thread = new Thread(new DownloadFileTask());

        System.out.println(Thread.currentThread().getName());
        thread.start(); // ==> run

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.interrupt();
    }
}