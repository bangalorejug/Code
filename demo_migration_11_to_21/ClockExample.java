import java.time.Clock;

public class ClockExample {
    public static void main(String[] args) {
        System.out.println(Clock.systemUTC().instant());
    }
}
