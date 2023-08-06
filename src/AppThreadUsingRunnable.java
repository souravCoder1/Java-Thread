
class RunnerTR1 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1: "+i);
        }
    }
}

//class RunnerTR2 implements Runnable {
//    public void run() {
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Runner2: "+i);
//        }
//    }
//}

public class AppThreadUsingRunnable {
    public static void main(String[] args) {

        // Not PARALLEL Execution
        // achieve multithreading
        Thread t1 = new Thread(new RunnerTR1());
        //Thread t2 = new Thread(new RunnerTR2());
        Thread t2 = new Thread(new Runnable() {  //Anonymous inner class
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Runner2: "+i);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
