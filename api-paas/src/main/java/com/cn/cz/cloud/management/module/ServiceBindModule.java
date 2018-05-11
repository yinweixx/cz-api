package com.cn.cz.cloud.management.module;

import com.cn.cz.cloud.management.service.ApiTestService;
import com.cn.cz.cloud.management.service.impl.ApiTestServiceImpl;
import com.google.inject.AbstractModule;

/**
 * @author ywaz
 * @date 5/11/18 16:07
 */
public class ServiceBindModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(ApiTestService.class).to(ApiTestServiceImpl.class);
    }
}
