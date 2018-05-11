package com.cn.cz.cloud.management.api;


import com.cn.cz.cloud.common.json.JsonUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/index")
public class IndexAPI {

    @Path("/test")
    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
    public String test(){
        return JsonUtil.toJson(null);
    }
}
