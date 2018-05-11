package com.cn.cz.cloud.management.api;

import com.cn.cz.cloud.common.db.Database;
import com.cn.cz.cloud.common.json.JsonUtil;
import com.google.inject.Guice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/index")
public class IndexAPI {

    @Path("/test")
    @GET
    public String test(){

        Database database = Guice.createInjector().getInstance(Database.class);

        System.out.println(database.getConfig().getJdbcDriver());
        return JsonUtil.toJson(null);
    }
}
