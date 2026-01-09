
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FixedThreadPool {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newFixedThreadPool(2) ) {
            for(int i=0; i<5; i++){
                service.execute(new Task(i));
            }
        } catch (Exception e) {
        }
    }
}

class Task implements Runnable{
    private final int taskId;
    public Task(int taskId){
        this.taskId = taskId;
    }
    @Override
    public void run() {
        try {
            System.out.println("The task with taskId " + taskId + " is being executed by " + Thread.currentThread().getName());
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}