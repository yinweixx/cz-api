package com.cn.cz.cloud.common.base;

import com.cn.cz.cloud.common.db.Database;
import com.cn.cz.cloud.common.etcd.Etcd;
import com.cn.cz.cloud.common.nats.Nats;

/**
 * @author ywaz
 * @date 5/10/18 16:55
 */
public interface Context {
    Database getDataBase();

    Context init() throws Exception;

    Etcd getEtcd();

    Nats getNats();

    <T> T getBeanByClass(Class<T> type);
}
