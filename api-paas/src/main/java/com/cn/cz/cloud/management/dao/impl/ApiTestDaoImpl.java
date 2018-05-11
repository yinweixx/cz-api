package com.cn.cz.cloud.management.dao.impl;

import com.cn.cz.cloud.common.base.Context;
import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.common.db.AbstractIciqlDao;
import com.cn.cz.cloud.common.db.Database;
import com.cn.cz.cloud.management.dao.ApiTestDao;
import com.cn.cz.cloud.management.entity.ApiTestEntity;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author ywaz
 * @date 5/11/18 16:13
 */
public class ApiTestDaoImpl extends AbstractIciqlDao implements ApiTestDao{

    private static Logger LOGGER = LoggerFactory.getLogger(ApiTestDaoImpl.class);

    @Inject
    public ApiTestDaoImpl(Database database) {
        super(database);
    }

    @Override
    public List<ApiTestEntity> queryTest() throws RuntimeException {

        System.out.println(InjectorsBuilder.getBuilder().getInstanceByType(Context.class).getDataBase().getConfig().toString());
        String sql = "select * from test";
        ResultSet rs = db.executeQuery(sql);
        List<ApiTestEntity> list = db.buildObjects(ApiTestEntity.class,rs);
        System.out.println("-----------------------------");
        System.out.println(list.size());
        return list;
    }
}
