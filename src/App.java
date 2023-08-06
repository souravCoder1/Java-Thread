public class App {
    static int count1 = 0;
    static int count2 = 0;
    static int count3 = 0;
    static int count4 = 0;

    // because object has a single lock, this is why the methods cannot be executed "at the same time" - Time Slicing algorithm.
    public static synchronized void increment1() {
        count1++;
        System.out.println(Thread.currentThread().getName() +"   "+ count1);
    }

    public static synchronized void increment2() {
        count2++;
        System.out.println(Thread.currentThread().getName() +"   "+ count2);
    }

    public static void increment3() {
        // Using Synchronise block is a good practice
        synchronized (App.class) { // class level locking
            count3++;
            System.out.println(Thread.currentThread().getName() + "   " + count3);
        }
    }

    public void increment4() {
        // Using Synchronise block is a good practice
        synchronized (this) { // object level locking
            count4++;
            System.out.println(Thread.currentThread().getName() + "   " + count4);
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
        System.out.println("Total Counter 2 : " + count2);

    }
}
