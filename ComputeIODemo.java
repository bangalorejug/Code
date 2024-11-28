import java.io.FileWriter;
import java.io.IOException;

public class ComputeIODemo {
    public static void main(String[] args) {
        int numThreads = 2; // Number of threads to simulate workload

        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                performComputation(); // 50% compute task
                performIO();          // 50% I/O task
            }).start();
        }
    }

    // Simulate a compute-intensive task
    private static void performComputation() {
        long sum = 0;
        for (int i = 0; i < 10_000_000; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " Computation result: " + sum);
    }

    // Simulate an I/O-blocking task
    private static void performIO() {
        synchronized (ComputeIODemo.class) { // Simulate synchronized I/O
            try (FileWriter writer = new FileWriter("output.txt", true)) {
                writer.write(Thread.currentThread().getName() + " wrote data\n");
                writer.flush(); // Simulate blocking I/O
                Thread.sleep(100); // Simulate slow I/O operation
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
