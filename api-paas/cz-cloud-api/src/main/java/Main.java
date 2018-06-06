import com.cn.cz.cloud.common.base.Context;
import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.management.module.BaseBindModule;
import com.cn.cz.cloud.management.host.startup.daemon.StartupClass;
import com.cn.cz.cloud.management.module.ClientBindModule;
import com.cn.cz.cloud.management.module.DaoBindModule;
import com.cn.cz.cloud.management.module.ServiceBindModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        InjectorsBuilder injectorsBuilder = InjectorsBuilder.getBuilder().builder(
            new ClientBindModule(),
            new BaseBindModule(),
            new ServiceBindModule(),
            new DaoBindModule()
        );

        injectorsBuilder.getKernelInjector().getInstance(Context.class).init();
        injectorsBuilder.getKernelInjector().getInstance(StartupClass.class).startup();
        LOGGER.info("service is started");
    }
}
