public class Synchronization {
    private static int counter = 0;

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for(int i=0; i<10000; i++)increment();
        });
        Thread two  = new Thread(() -> {
            for(int i=0; i<10000; i++)increment();
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("The value of counter is "+ counter);
        }
    }
    private  synchronized static void increment(){
        counter++;
    }
}
