package com.cn.cz.cloud.management.module;

import com.cn.cz.cloud.common.mysql.ClientConfig;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ywaz
 * @date 5/10/18 16:36
 */
public class MySQLClientBindModule extends AbstractModule{
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLClientBindModule.class);

    @Override
    protected void configure() {
        ClientConfig clientConfig = new ClientConfig();
        bind(ClientConfig.class).toInstance(clientConfig);
        LOGGER.info("Bind mysql client config to :{}",clientConfig.toString());
    }
}
