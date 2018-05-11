package com.cn.cz.cloud.common.nats.params;

public interface MessageFormater {

    /**
     *
     * @param business
     * @param obj
     * @return
     */
    byte[] format(String business, Object obj);

    default String getApplication(){
        return "";
    }
}