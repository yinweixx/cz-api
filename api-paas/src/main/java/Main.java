import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        String runtimeMXBeanName = ManagementFactory.getRuntimeMXBean().getName();
        LOGGER.info("runtimeMXBean's name is" + runtimeMXBeanName);

    }
}
