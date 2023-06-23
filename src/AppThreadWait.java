
class RunnerTW1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner1: "+i);
        }
    }
}

class RunnerTW2 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2: "+i);
        }
    }
}

public class AppThreadWait {
    public static void main(String[] args) {

        // Not PARALLEL Execution
        // achieve multithreading
        Thread t1 = new RunnerTW1();
        Thread t2 = new RunnerTW2();

        t1.start();
        t2.start();

        //  Wait till the tread to finish

        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finished with threads....");
    }
}
