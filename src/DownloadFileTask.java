public class DownloadFileTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Downloading files...."+ Thread.currentThread().getName()); // 1

        for (int i = 0; i< Integer.MAX_VALUE; i++) {
            if(Thread.currentThread().isInterrupted())
                break;

            System.out.println("Downloading byte " + i);
        }

        System.out.println("Download complete: "+ Thread.currentThread().getName()); // 3
    }
}