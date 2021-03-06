package com.cn.cz.cloud.management.module;

import com.cn.cz.cloud.common.base.Context;
import com.cn.cz.cloud.common.base.impl.DefaultContext;
import com.cn.cz.cloud.management.host.startup.daemon.JettyRestServer;
import com.cn.cz.cloud.management.host.startup.daemon.StartupClass;
import com.cn.cz.cloud.management.host.startup.daemon.impl.DaemonStartup;
import com.cn.cz.cloud.management.host.startup.daemon.impl.JettyRestServerImpl;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ywaz
 * @date 5/11/18 10:48
 */
public class BaseBindModule extends AbstractModule{
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseBindModule.class);

    @Override
    protected void configure() {
        bind(Context.class).to(DefaultContext.class);
        bind(StartupClass.class).to(DaemonStartup.class);
        bind(JettyRestServer.class).to(JettyRestServerImpl.class);
    }
}
