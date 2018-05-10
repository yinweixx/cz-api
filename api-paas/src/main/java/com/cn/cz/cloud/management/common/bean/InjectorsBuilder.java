package com.cn.cz.cloud.management.common.bean;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author ywaz
 * @date 5/10/18 15:32
 */
public class InjectorsBuilder {
    private static InjectorsBuilder builder;
    private Injector kernelInjector;

    private InjectorsBuilder(){}

    public static InjectorsBuilder getBuilder(){
        synchronized (InjectorsBuilder.class){
            if (builder == null)
                builder = new InjectorsBuilder();
        }
        return builder;
    }

    public InjectorsBuilder builder(AbstractModule... modules){
        if (kernelInjector != null)
            kernelInjector = null;
        kernelInjector = Guice.createInjector(modules);
        return this;
    }

    public <T> T getInstance(Class<T> type){
        return kernelInjector.getInstance(type);
    }

    public Injector getKernelInjector() {
        return kernelInjector;
    }
}
