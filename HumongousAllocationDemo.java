import java.util.ArrayList;
import java.util.List;

public class HumongousAllocationDemo {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Humongous Allocation Demo...");
        
        // List to hold references to prevent garbage collection
        List<byte[]> allocations = new ArrayList<>();

        // Allocate large objects continuously
        try {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Allocating humongous object #" + (i + 1));
                // Each object is 6MB (adjust based on your G1GC region size to exceed 50%)
                allocations.add(new byte[6 * _1MB]); 
                Thread.sleep(100); // Slow down to observe GC behavior
            }
        } catch (OutOfMemoryError e) {
            System.err.println("Out of memory! Excessive humongous allocation caused the problem.");
        }
    }
}
