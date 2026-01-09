import java.util.ArrayList;
import java.util.List;


public class Worker {
    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private final Object lock = new Object();
    private final List<Integer> container;

    public Worker(Integer top, Integer bottom){
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException{
        synchronized (lock) {
            
        
            while (true) { 
                if(container.size() == top){
                    System.out.println("Wait for sequence to be consumed %n ");
                    lock.wait();
                }else{
                    System.out.printf("sequence added to container %d%n", sequence);
                    container.add(sequence++);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException{
        synchronized (lock) {
        while(true){
            if(container.size()<=0){
                System.out.println("Waiting for container to have sequence %n");
                lock.wait();
            }else{
                System.out.printf("consuming from container %d%n", container.removeFirst());
                //container.remove(0);
                lock.notify();

            }
            Thread.sleep(500);
        }
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker(10, 0);
        Thread producer = new Thread(()-> {
            try {
                worker.produce();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(()->{
            try {
                worker.consume();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
    }
}
