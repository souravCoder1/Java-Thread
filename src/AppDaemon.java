
class DaemonWorker implements Runnable {
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon thread is running...");
        }
    }
}

class Worker implements Runnable {
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Normal thread finishes execution...");

    }
}

public class AppDaemon {
    public static void main(String[] args) {

        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new Worker());

        t1.setDaemon(true);
        t1.start();
        t2.start();
        System.out.println(t1.isDaemon());
    }
}
