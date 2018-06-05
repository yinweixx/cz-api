package com.cn.cz.cloud.common.base.impl;

import com.cn.cz.cloud.common.base.Context;
import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.common.db.Database;
import com.cn.cz.cloud.common.etcd.Etcd;
import com.cn.cz.cloud.common.nats.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author ywaz
 * @date 5/11/18 13:28
 */
public class DefaultContext implements Context {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultContext.class);
    private Database database;
//    private Etcd etcd;
    private Nats nats;
    private String deviceId;

    @Inject
    public DefaultContext(Database database,Nats nats) {
        this.database = database;
//        this.etcd = etcd;
        this.nats = nats;
    }

    @Override
    public Database getDataBase() {
        return database;
    }

    @Override
    public Context init() throws Exception {
        try{
            database.connect();
            nats.connect();
            nats.startMessageMonitor();
        } catch (Exception ex){
            throw new Exception(ex);
        }
        return this;
    }

    @Override
    public Etcd getEtcd() {
        return null;
    }

    @Override
    public Nats getNats() {
        return nats;
    }

    @Override
    public <T> T getBeanByClass(Class<T> type) {
        return InjectorsBuilder.getBuilder().getInstanceByType(type);
    }
}
