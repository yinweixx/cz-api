package com.cn.cz.cloud.management.service.impl;

import com.cn.cz.cloud.common.dispath.ErrorCode;
import com.cn.cz.cloud.common.dispath.Response;
import com.cn.cz.cloud.management.dao.ApiTestDao;
import com.cn.cz.cloud.management.entity.ApiTestEntity;
import com.cn.cz.cloud.management.service.ApiTestService;

import javax.inject.Inject;

/**
 * @author ywaz
 * @date 5/11/18 15:57
 */

public class ApiTestServiceImpl implements ApiTestService{

    private ApiTestDao apiTestDao;

    @Inject
    public ApiTestServiceImpl(ApiTestDao apiTestDao){
        this.apiTestDao = apiTestDao;
    }

    @Override
    public Response queryDetails() {
        Response response = new Response<ApiTestEntity>();
        apiTestDao.queryIntegerTest();
        response.setCode(ErrorCode.NO_ERROR.code());
        return null;
    }
}
