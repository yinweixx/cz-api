package com.cn.cz.cloud.management.host.startup.daemon.impl;

import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.management.host.startup.daemon.JettyRestServer;
import com.cn.cz.cloud.management.host.startup.daemon.StartupClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.concurrent.CountDownLatch;

/**
 * @author ywaz
 * @date 5/11/18 10:33
 */
public class DaemonStartup implements StartupClass {
    private static Logger LOGGER = LoggerFactory.getLogger(DaemonStartup.class);
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Inject
    public DaemonStartup(){}

    @Override
    public void startup() throws Exception {
        LOGGER.info("start jetty server");
        JettyRestServer jettyRestServer = InjectorsBuilder.getBuilder().getInstanceByType(JettyRestServer.class);
        jettyRestServer.start();
//        String daemonOperate = StartupOption.getOption().getValue("doemon.type");
//        if (StringUtils.isEmpty(daemonOperate)){
//            return;
//        }
//
//        switch (daemonOperate){
//            case START_CLASS_TYPE:
//                JettyRestServer jettyRestServer = InjectorsBuilder.getBuilder().getInstanceByType(JettyRestServer.class);
//                jettyRestServer.start();
//                countDownLatch.await();
//            case STOP_CLASS_TYPE:
//                break;
//        }
    }
}
