import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IOIntensiveDemo {

    public static void main(String[] args) throws InterruptedException {
        int numThreads = Runtime.getRuntime().availableProcessors() * 4; // Common formula for I/O tasks
        System.out.println("Number of threads: " + numThreads);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads * 2; i++) {
            executor.submit(IOIntensiveDemo::simulateIO);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
    }

    private static void simulateIO() {
        try {
            System.out.println(Thread.currentThread().getName() + " - Simulating I/O");
            Thread.sleep(2000); // Simulate I/O blocking with sleep
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
