public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount());
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 1. Main Thread
        // 2. Background (Daemon)
        DownloadStatus downloadStatus = new DownloadStatus();
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.show(downloadStatus); // main thread

    }
}
