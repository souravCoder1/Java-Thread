public class AppLock {
    static int count1 = 0;
    static int count2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void increment1() {
        synchronized (lock1) {
            count1++;
            System.out.println(Thread.currentThread().getName() + "   " + count1);
        }
    }

    public static void increment2() {
        synchronized (lock2) {
            count2++;
            System.out.println(Thread.currentThread().getName() + "   " + count2);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    increment1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    increment2();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total Counter 1 : " + count1);
        System.out.println("Total Counter 2 : " + count2);    }
}
