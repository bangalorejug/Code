import java.util.Random;

public class MinMaxVectorTest {

    private static final int SIZE = 100_000_000;
    private static final long LO = -1_000_000_000L;
    private static final long HI = 1_000_000_000L;

    public static void main(String[] args) {
        long[] data = new long[SIZE];
        Random rnd = new Random(42);
        for (int i = 0; i < SIZE; i++) {
            data[i] = rnd.nextLong();
        }

        // Warmup
        for (int i = 0; i < 3; i++) {
            runAll(data);
        }

        // Actual timed runs
        System.out.println("==== Timed Runs ====");
        runAll(data);
    }

    private static void runAll(long[] data) {
        time("mathClip",   () -> mathClip(data, LO, HI));
        time("mathSeparate", () -> mathSeparate(data, LO, HI));
        System.out.println();
    }

    private static void time(String name, Runnable r) {
        long t0 = System.nanoTime();
        r.run();
        long t1 = System.nanoTime();
        double ms = (t1 - t0) / 1_000_000.0;
        System.out.printf("%-15s : %8.2f ms%n", name, ms);
    }

    private static long mathClip(long[] arr, long lo, long hi) {
        long sum = 0L;
        for (long v : arr) {
            v = Math.min(Math.max(v, lo), hi);
            sum += v;
        }
        return sum;
    }

    private static long mathSeparate(long[] arr, long lo, long hi) {
        long sum = 0L;
        for (long v : arr) {
            long tmp = Math.max(v, lo);
            tmp = Math.min(tmp, hi);
            sum += tmp;
        }
        return sum;
    }
}

