package com.cn.cz.cloud.management.module;

import com.cn.cz.cloud.management.dao.ApiTestDao;
import com.cn.cz.cloud.management.dao.impl.ApiTestDaoImpl;
import com.google.inject.AbstractModule;

/**
 * @author ywaz
 * @date 5/11/18 16:13
 */
public class DaoBindModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(ApiTestDao.class).to(ApiTestDaoImpl.class);
    }
}
