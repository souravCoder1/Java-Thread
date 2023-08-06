
class RunnerTS1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner1: "+i);
        }
    }
}

class RunnerTS2 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner2: "+i);
        }
    }
}

public class AppThreadWithSleep {
    public static void main(String[] args) {

        // Not PARALLEL Execution
        // achieve multithreading
        Thread t1 = new RunnerTS1();
        Thread t2 = new RunnerTS2();

        t1.start();
        t2.start();
    }
}
