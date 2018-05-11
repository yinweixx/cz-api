package com.cn.cz.cloud.common.db;

import com.cn.cz.cloud.common.mysql.ClientConfig;

import javax.inject.Inject;

/**
 * @author ywaz
 * @date 5/10/18 16:57
 */
public class DatabaseConfig {
    public static final String VALIDATION_SQL = "select 1=1";
    public static final int DEFAULT_INIT_SIZE = 10;
    public static final int DEFAULT_MAX_SIZE = 50;
    public static final int DEFAULT_VALIDATION_TIMEOUT = 60 * 10;
    private String validationSql;
    private int poolInitSize;
    private int poolMaxSize;
    private int validationTimeOut;
    private String jdbcDriver;
    private String jdbcUri;
    private String jdbcUser;
    private String jdbcPasswd;
    private ClientConfig mysql;

    @Inject
    public DatabaseConfig(ClientConfig clientConfig) {
        this.mysql = clientConfig;
        this.jdbcUri = mysql.getUrl();
        this.jdbcUser = mysql.getUsername();
        this.jdbcPasswd = mysql.getPassword();
        this.jdbcDriver = mysql.getDriver();
        this.validationSql = VALIDATION_SQL;
        this.poolInitSize = DEFAULT_INIT_SIZE;
        this.poolMaxSize = DEFAULT_MAX_SIZE;
        this.validationTimeOut = DEFAULT_VALIDATION_TIMEOUT;
    }


    public String getValidationSql() {
        return validationSql;
    }

    public int getPoolInitSize() {
        return poolInitSize;
    }

    public int getPoolMaxSize() {
        return poolMaxSize;
    }

    public int getValidationTimeOut() {
        return validationTimeOut;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getJdbcUri() {
        return jdbcUri;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public String getJdbcPasswd() {
        return jdbcPasswd;
    }

    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "validationSql='" + validationSql + '\'' +
                ", poolInitSize=" + poolInitSize +
                ", poolMaxSize=" + poolMaxSize +
                ", validationTimeOut=" + validationTimeOut +
                ", jdbcDriver='" + jdbcDriver + '\'' +
                ", jdbcUri='" + jdbcUri + '\'' +
                ", jdbcUser='" + jdbcUser + '\'' +
                ", jdbcPasswd='" + jdbcPasswd + '\'' +
                ", mysql=" + mysql +
                '}';
    }
}
