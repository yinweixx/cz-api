import com.cn.cz.cloud.common.base.Context;
import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.management.module.BaseBindModule;
import com.cn.cz.cloud.management.host.startup.daemon.StartupClass;
import com.cn.cz.cloud.management.module.ClientBindModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        InjectorsBuilder injectorsBuilder = InjectorsBuilder.getBuilder().builder(
            new ClientBindModule(),
            new BaseBindModule()
        );

        injectorsBuilder.getKernelInjector().getInstance(Context.class).init();
        injectorsBuilder.getKernelInjector().getInstance(StartupClass.class).startup();
    }
}
