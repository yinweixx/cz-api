import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.management.module.BaseBindModule;
import com.cn.cz.cloud.management.host.startup.daemon.StartupClass;
import com.cn.cz.cloud.tools.startup.StartupOption;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
//        String runtimeMXBeanName = ManagementFactory.getRuntimeMXBean().getName();
//        LOGGER.info("runtimeMXBean's name is" + runtimeMXBeanName);
//
//        StartupOption.getOption().init(args);
//        String startupType = StartupOption.getOption().getValue(StartupOption.STARTUP_TYPE);
//        if (StringUtils.isEmpty(startupType)){
//            System.exit(1);
//        }
//
        InjectorsBuilder.getBuilder().builder(
            new BaseBindModule()
        );

        StartupClass startup = InjectorsBuilder.getBuilder().getInstanceByType(StartupClass.class);
        startup.startup();
    }
}
