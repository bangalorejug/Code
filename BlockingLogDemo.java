import java.io.FileWriter;
import java.io.IOException;

public class BlockingLogDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> logMessage("Thread: " + Thread.currentThread().getName())).start();
        }
    }

    static void logMessage(String message) {
        synchronized (lock) {
            try (FileWriter writer = new FileWriter("log.txt", true)) {
                writer.write(message + "\n");
                Thread.sleep(100);  // Simulates slow I/O
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
