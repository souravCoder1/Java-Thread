public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount());
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 1. Main Thread
        // 2. Background (Daemon)
        DownloadStatus downloadStatus = new DownloadStatus();
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.show(downloadStatus); // main thread
        String s1= "Abc.";
        System.out.println(s1.hashCode());
        System.out.println(s1);
        s1= s1.toUpperCase();
        System.out.println(s1.hashCode());
        System.out.println(s1);



        Integer s2= 52;
        System.out.println(s2.hashCode());

        s2 = -52;
        System.out.println(s2.hashCode());


    }
}
