
public class Main {

    public static void main(String[] args) {
        System.out.println(Thread.activeCount());
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 1. Main Thread
        // 2. Background Thread (Daemon)
        Processor processor = new Processor();
        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.producer();
            }
        }); // this will be an anonymous class
        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadProducer.start();
        threadConsumer.start();
    }
}
