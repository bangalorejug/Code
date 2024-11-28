import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreamLocality {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        IntStream.range(0, 300_000_000).forEach(numbers::add);

        long startTime = System.nanoTime();
       int sum = IntStream.range(0, 300_000_000).parallel().sum();
    //    int sum = numbers.parallelStream().mapToInt(Integer::intValue).sum();  // Poor locality with Integer objects
        long endTime = System.nanoTime();

        System.out.println("Sum: " + sum);
        System.out.println("Time taken: " + (endTime - startTime) / 1e6 + " ms");
    }
}
