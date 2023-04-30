import java.util.ArrayList;
import java.util.List;

public class Processor {
    
    List<Integer> list = new ArrayList<>();
    private static final  int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;

    int value = 1;
    void producer() { // 1, 2 ,3, 4
        synchronized (this) { // only one thread gets the access
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting to remove items.....");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Producing :" + value);
                    list.add(value);
                    value++;
                    notify(); // if any other thread is waiting then it will wake up that thread
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    void consumer() throws InterruptedException { // 4, 3, 2, 1
        synchronized (this) { // only one thread gets the access
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting to adding items.....");
                    wait();
                } else {
                    System.out.println("Consuming :" + list.get(list.size() - 1));
                    list.remove(list.size() - 1);
                    notify();
                    //...............
                    System.out.println("abc");
                }
                Thread.sleep(1000);
            }
        }
    }
}
