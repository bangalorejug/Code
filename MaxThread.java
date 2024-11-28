/*
 * A small demo to show how fast virtual threads can unblock the CPU in case of blocking operations
 */

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class MaxThread {
    public static void main(String[] args) throws InterruptedException {
        var threads = IntStream.range(0, 100000).mapToObj(index -> Thread.ofVirtual().unstarted(() -> {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })).toList();

        Instant begin = Instant.now();
        var i = 0;
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
        Instant end = Instant.now();
        System.out.println("Duration = " + Duration.between(begin, end).getSeconds() + " seconds");
    }
}
