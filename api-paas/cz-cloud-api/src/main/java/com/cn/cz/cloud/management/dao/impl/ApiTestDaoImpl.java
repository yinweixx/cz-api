package com.cn.cz.cloud.management.dao.impl;

import com.cn.cz.cloud.common.db.AbstractIciqlDao;
import com.cn.cz.cloud.common.db.Database;
import com.cn.cz.cloud.common.etcd.EtcdClient;
import com.cn.cz.cloud.management.dao.ApiTestDao;
import com.cn.cz.cloud.management.entity.ApiTestEntity;
import com.coreos.jetcd.data.ByteSequence;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author ywaz
 * @date 5/11/18 16:13
 */
public class ApiTestDaoImpl extends AbstractIciqlDao implements ApiTestDao{

    private static Logger LOGGER = LoggerFactory.getLogger(ApiTestDaoImpl.class);

    private static EtcdClient etcdClient = EtcdClient.GetEtcdClient();

    @Inject
    public ApiTestDaoImpl(Database database) {
        super(database);
    }

    @Override
    public List<ApiTestEntity> queryTest(){
//        String sql = "select * from test";
        String sql = "update test set num = num + 1 where id = 1";
        try {
            db.executeUpdate(sql);
//            ResultSet rs = db.executeQuery(sql);
//            List<ApiTestEntity> list = db.buildObjects(ApiTestEntity.class,rs);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Integer> queryIntegerTest() {

//        ByteSequence key = null;
//        try {
//            key = etcdClient.lockLeaseKey("update_test_num",2);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ApiTestEntity apiTestEntity = new ApiTestEntity();

        List<Integer> list = db.from(apiTestEntity).where(apiTestEntity.id).is(1).selectDistinct(apiTestEntity.num);

        LOGGER.info(String.valueOf(list.get(0)));

        int i = list.get(0) + 1;

        String sql = "update test set num = " + i + " where id = 1";

        try {
            db.executeUpdate(sql);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
//        finally {
//            etcdClient.unLockLeaseKey(key);
//        }
        return null;
    }
}
