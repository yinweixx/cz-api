package com.cn.cz.cloud.management.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ywaz
 * @date 5/10/18 15:29
 */

public class JsonUtil {
    public static <T> T fromJson(Class<T> type,String json) {
        try {
            GsonBuilder gb = new GsonBuilder();
            gb.disableHtmlEscaping();
            Gson gson = gb.create();
            return gson.fromJson(json,type);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object obj) {
        try {
            GsonBuilder gb = new GsonBuilder();
            gb.disableHtmlEscaping();
            Gson gson = gb.create();
            return gson.toJson(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object obj,Class clazz) {
        try {
            GsonBuilder gb = new GsonBuilder();
            gb.disableHtmlEscaping();
            Gson gson = gb.create();
            return gson.toJson(obj,clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
