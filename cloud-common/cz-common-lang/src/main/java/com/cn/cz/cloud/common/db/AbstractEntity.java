package com.cn.cz.cloud.common.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ywaz
 * @date 5/11/18 11:14
 */
public class AbstractEntity implements BaseEntity<AbstractEntity>{
    @Override
    public String asJson() {
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        return gson.toJson(this);
    }

    @Override
    public AbstractEntity build() {
        return this;
    }
}
