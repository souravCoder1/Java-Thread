public class ThreadDemo { // current is main
    void show() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());

        Thread thread = new Thread(new DownloadFileTask());
        thread.start(); // ==> run

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("File is ready to be scanned!!");
    }
}
