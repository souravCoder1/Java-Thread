import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("Task with id: "+id+" is in work- thread id: "+ Thread.currentThread().getName());
        double duration = Math.random()*10;
        try {
            TimeUnit.SECONDS.sleep((long) duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class SingleThreadExecutor {
    public static void main(String[] args) {
        /*
        * it' a single thread that executes the tas sequentially. So one after another
        * */
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++){
            executor.submit(new Task(i));
        }

        // program not terminated....because we have to shut down the executor manually!!!!
    }
}
