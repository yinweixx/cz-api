package com.cn.cz.cloud.management.api;

import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.common.json.JsonUtil;
import com.cn.cz.cloud.management.service.ApiTestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/index")
public class IndexAPI {

    private static ApiTestService apiTestService = InjectorsBuilder.getBuilder().getInstanceByType(ApiTestService.class);

    @Path("/test")
    @GET
    public String test(){
        apiTestService.queryDetails();

        return JsonUtil.toJson(null);
    }
}
