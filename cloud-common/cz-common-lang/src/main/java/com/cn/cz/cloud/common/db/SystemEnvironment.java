package com.cn.cz.cloud.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ywaz
 * @date 5/11/18 09:08
 */
public class SystemEnvironment {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemEnvironment.class);
    public static final String DB_VALIDATION_SQL = "VALIDATION_SQL";
    public static final String DB_INIT_SIZE = "DB_INIT_SIZE";
    public static final String DB_MAX_SIZE = "DB_MAX_SIZE";
    public static final String DB_VALIDATION_TIMEOUT = "DB_VALIDATION_TIMEOUT";
    public static final String DB_JDBC_URI = "DB_JDBC_URI";
    public static final String DB_JDBC_USR = "DB_JDBC_USR";
    public static final String DB_JDBC_PWD = "DB_JDBC_PWD";
    public static final String DB_JDBC_DRIVER = "DB_JDBC_DRIVER";
    public static final String ETCD_URL = "ETCD_URL";
    public static final String NATS_URL = "NATS_URL";
    public static final String ELASTICSEARCH_URL = "ELASTICSEARCH_URL";
    public static final String ELASTICSEARCH_USER_NAME = "ELASTICSEARCH_USER_NAME";
    public static final String ELASTICSEARCH_PASSWORD = "ELASTICSEARCH_PASSWORD";


    private Map<String, String> sysenv = new HashMap<>();

    public SystemEnvironment() {
        this.sysenv.putAll(System.getenv());
    }

    public SystemEnvironment(Map<String, String> sysenv) {
        this.sysenv = sysenv;
    }

    public Map<String, String> getSysenv() {
        return sysenv;
    }

    public String getEnv(String name) {
        return sysenv.get(name);
    }
}
