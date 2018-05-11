package com.cn.cz.cloud.management.host.startup.daemon;

/**
 * @author ywaz
 * @date 5/11/18 10:30
 */
public interface StartupClass {
    String START_CLASS_TYPE = "start";
    String STOP_CLASS_TYPE = "stop";

    void startup() throws Exception;
}
