package com.cn.cz.cloud.management.common.db;

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
    private SystemEnvironment environment;
    private String jdbcDriver;
    private String jdbcUri;
    private String jdbcUser;
    private String jdbcPasswd;

    public DatabaseConfig(SystemEnvironment environment) {
        if (environment == null)
            return;
        this.environment = environment;
        jdbcUri = environment.getEnv(SystemEnvironment.DB_JDBC_URI);
        jdbcUser = environment.getEnv(SystemEnvironment.DB_JDBC_USR);
        jdbcPasswd = environment.getEnv(SystemEnvironment.DB_JDBC_PWD);
        jdbcDriver = environment.getEnv(SystemEnvironment.DB_JDBC_DRIVER);
        if ((validationSql = environment.getEnv(SystemEnvironment.DB_VALIDATION_SQL)) == null) {
            validationSql = VALIDATION_SQL;
        }
        if ((poolInitSize = convertInt(SystemEnvironment.DB_INIT_SIZE)) == 0) {
            poolInitSize = DEFAULT_INIT_SIZE;
        }
        if ((poolMaxSize = convertInt(SystemEnvironment.DB_MAX_SIZE)) == 0) {
            poolMaxSize = DEFAULT_MAX_SIZE;
        }
        if ((validationTimeOut = convertInt(SystemEnvironment.DB_VALIDATION_TIMEOUT)) == 0) {
            validationTimeOut = DEFAULT_VALIDATION_TIMEOUT;
        }
    }

    public DatabaseConfig() {
    }

    private int convertInt(String name) {
        String value = environment.getEnv(name);
        if (value == null || value.equals("")) {
            return 0;
        }
        try {
            return Integer.valueOf(value);
        } catch (Exception ex) {
            return 0;
        }
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
}
