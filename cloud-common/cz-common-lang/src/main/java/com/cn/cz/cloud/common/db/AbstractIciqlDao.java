package com.cn.cz.cloud.common.db;

import com.iciql.Db;

public abstract class AbstractIciqlDao {
    protected Db db;

    public AbstractIciqlDao(Database database) {
        this.db = database.getDb();
    }
}