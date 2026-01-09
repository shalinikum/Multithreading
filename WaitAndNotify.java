

public class WaitAndNotify {
    
    private static Object LOCK = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{
            try {
                increment1();
            } catch (Exception e) {
                throw  new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()->{
            increment2();
        });
        t1.start();
        t2.start();

    }
    private static void increment1() throws InterruptedException{
        synchronized (LOCK) {
            System.out.println("Hello from method One");
            LOCK.wait();
            System.out.println("Hello from method one again");
        }
    }
    private static void increment2(){
        synchronized (LOCK) {
            System.out.println("Hello from method two");
            LOCK.notify();
            System.out.println("Hello from method two again");
        }
    }
}
