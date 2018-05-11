package com.cn.cz.cloud.common.db;

import com.iciql.Db;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author ywaz
 * @date 5/10/18 16:56
 */
@Singleton
public class Database {
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);
    private Db db;
    private DatabaseConfig config;

    @Inject
    public Database(DatabaseConfig config){
        this.config = config;
    }

    public void connect() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(config.getJdbcDriver());
        ds.setValidationQuery(config.getValidationSql());
        ds.setValidationQueryTimeout(config.getValidationTimeOut());
        ds.setInitialSize(config.getPoolInitSize());
        ds.setMaxTotal(config.getPoolMaxSize());
        ds.setUrl(config.getJdbcUri());
        ds.setUsername(config.getJdbcUser());
        ds.setPassword(config.getJdbcPasswd());
        try {
            LOGGER.debug("connection to db: {}", config.getJdbcUri());
            db = Db.open(ds);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public Db getDb() {
        return db;
    }
}
