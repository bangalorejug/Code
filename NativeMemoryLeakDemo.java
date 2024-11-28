import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class NativeMemoryLeakDemo {
    public static void main(String[] args) throws Exception {
        List<ByteBuffer> bufferList = new ArrayList<>();
        System.out.println("Starting Native Memory Leak Demo...");

        try {
            while (true) {
                // Allocate a 10MB direct buffer (outside the JVM heap)
                ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024); // 10 MB
                bufferList.add(buffer); // Keep references to prevent garbage collection
                
                System.out.println("Allocated direct buffer #" + bufferList.size());
                
                // Slow down the allocation to observe native memory usage
                Thread.sleep(100); 
            }
        } catch (OutOfMemoryError e) {
            System.err.println("Native memory exhausted! Native memory leak detected.");
        }
    }
}
