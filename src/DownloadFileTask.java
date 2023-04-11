public class DownloadFileTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Downloading files...."+ Thread.currentThread().getName()); // 1

        try {
            Thread.sleep(5000); // approx  // 2
        } catch (Exception e) {
            System.out.println("Exception!!!!!");
            e.printStackTrace();
        }

        System.out.println("Download complete: "+ Thread.currentThread().getName()); // 3
    }
}
