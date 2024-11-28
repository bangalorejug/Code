import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GenerationalZGCDemo {
    public static void main(String[] args) {
        System.out.println("Starting memory allocation...");
        
        final int totalTasks = 1_00_000;
	long startTime = System.nanoTime();
        // Simulate workload with short-lived objects
        for (int i = 0; i < 10_000; i++) {
            createShortLivedObjects();
            if (i % 100 == 0) {
               // System.out.println("Iteration: " + i);
            }
        }
        long endTime = System.nanoTime();
	long elapsedTimeInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        double throughput = totalTasks / (elapsedTimeInMillis / 1000.0);
	System.out.println("Throughput: " + throughput + " tasks/second");
        System.out.println("Memory allocation completed.");
    }

    private static void createShortLivedObjects() {
        // Create a large number of short-lived objects
        List<int[]> temporaryList = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            temporaryList.add(new int[100]); // Short-lived objects
        }
        // The objects in temporaryList are discarded when the method ends
    }
}
