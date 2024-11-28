import java.math.BigInteger;
import java.util.function.BinaryOperator;

import java.time.Duration;
import java.time.Instant;


/**
 * This is a simple test class created to ensure that the results
 * of multiply() are the same as multiplyParallel(). We calculate
 * the Fibonacci numbers using Dijkstra's sum of squares to get
 * very large numbers (hundreds of thousands of bits).
 *
 * @author Heinz Kabutz, heinz@javaspecialists.eu
 */
public class BigIntegerParallelMultiplyTest {
    public static BigInteger fibonacci(int n, BinaryOperator<BigInteger> multiplyOperator) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        int half = (n + 1) / 2;
        BigInteger f0 = fibonacci(half - 1, multiplyOperator);
        BigInteger f1 = fibonacci(half, multiplyOperator);
        if (n % 2 == 1) {
            BigInteger b0 = multiplyOperator.apply(f0, f0);
            BigInteger b1 = multiplyOperator.apply(f1, f1);
            return b0.add(b1);
        } else {
            BigInteger b0 = f0.shiftLeft(1).add(f1);
            return multiplyOperator.apply(b0, f1);
        }
    }

    public static void main(String[] args) throws Exception {
        compare(100_000_000, 34712631);
    }

    private static void compare(int n, int expectedBitCount) {
        Instant start = Instant.now();
        BigInteger multiplyResult = fibonacci(n, BigInteger::multiply);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");


        Instant start1 = Instant.now();
        BigInteger parallelMultiplyResult = fibonacci(n, BigInteger::parallelMultiply);
        Instant end1 = Instant.now();
        Duration timeElapsed1 = Duration.between(start1, end1);
        System.out.println("Time taken: "+ timeElapsed1.toMillis() +" milliseconds");
        checkBitCount(n, expectedBitCount, multiplyResult);
        checkBitCount(n, expectedBitCount, parallelMultiplyResult);
        if (!multiplyResult.equals(parallelMultiplyResult))
            throw new AssertionError("multiply() and parallelMultiply() give different results");
    }

    private static void checkBitCount(int n, int expectedBitCount, BigInteger number) {
        System.out.println("Bit count is :  " + number.bitCount());
        if (number.bitCount() != expectedBitCount)
            throw new AssertionError(
                    "bitCount of fibonacci(" + n + ") was expected to be " + expectedBitCount
                            + " but was " + number.bitCount());
    }
}
