public class LockWithCustomObject {
    private static int counter1=0;
    private static int counter2=0;
    private static  Object lock1 = new Object();
    private static Object lock2 = new Object();
    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(()->{
                for(int i=0; i<10000; i++)increment1();
            });
            Thread t2 = new Thread(()->{
                for(int i=0; i<10000; i++)increment2();
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            
            System.out.println("Counter one value is: "+ counter1);
            System.out.println("Counter two value is: "+ counter2);
        } catch (InterruptedException ex) {
            System.getLogger(LockWithCustomObject.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    private static void increment1(){
        synchronized (lock1) {
            counter1++;
        }
    }
    private static void increment2(){
        synchronized (lock2) {
            counter2++;
        }
    }
}
