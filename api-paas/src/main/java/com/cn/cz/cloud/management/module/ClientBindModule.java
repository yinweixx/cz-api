package com.cn.cz.cloud.management.module;

import com.cn.cz.cloud.common.etcd.Etcd;
import com.cn.cz.cloud.common.etcd.EtcdConfig;
import com.cn.cz.cloud.common.etcd.impl.DefaultEtcd;
import com.cn.cz.cloud.common.mysql.ClientConfig;
import com.cn.cz.cloud.common.nats.Nats;
import com.cn.cz.cloud.common.nats.impl.DefaultNats;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ywaz
 * @date 5/10/18 16:36
 */
public class ClientBindModule extends AbstractModule{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientBindModule.class);

    @Override
    protected void configure() {
        bind(Etcd.class).to(DefaultEtcd.class);
//        bind(Nats.class).to(DefaultNats.class);
    }
}
