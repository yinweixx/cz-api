package com.cn.cz.cloud.management.dao;

import com.cn.cz.cloud.management.entity.ApiTestEntity;

import java.util.List;

/**
 * @author ywaz
 * @date 5/11/18 16:12
 */
public interface ApiTestDao {
    List<ApiTestEntity> queryTest() throws RuntimeException;
}
