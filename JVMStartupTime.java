import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class JVMStartupTime {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        
        // Get JVM uptime (milliseconds since JVM started)
        long uptime = runtimeMXBean.getUptime(); 
        
        System.out.println("JVM Startup Time: " + uptime + " ms");
    }
}
