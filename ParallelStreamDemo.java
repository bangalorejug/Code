import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.time.Instant;

/* Simple Demo to show the power of parallel processing. Please use primitive data type and run some mathematical calculation. 
   In this demo, first calculated the area and then sum all the area.
 */ 

public class ParallelStreamDemo {
    public static void main(String[] args) {
        double area[] = new double[700000000]; // Adjust the value as per the demo
        Random random = new Random();
        for(int r=0;r<700000000;r++)
            area[r] = Math.PI*r*r;


        System.out.println("Available Processor: " + Runtime.getRuntime().availableProcessors());

        Instant start = Instant.now();
        System.out.println(Arrays.
                stream(area).
                //   peek(e -> System.out.println(Thread.currentThread().getName())).   // to print thread info
                        sum());
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("-- GO SEQUENTIAL  --" + "Time taken: "+ timeElapsed.toMillis() +" milliseconds");



        Instant start1 = Instant.now();
        System.out.println(Arrays.
                stream(area).
                  parallel().
                //   peek(e -> System.out.println(Thread.currentThread().getName())).   // to print thread info
                        sum());
        Instant end1 = Instant.now();
        Duration timeElapsed1 = Duration.between(start1, end1);
        System.out.println("-- GO PARALLEL --" + "Time taken: "+ timeElapsed1.toMillis() +" milliseconds");
        
    }
}
