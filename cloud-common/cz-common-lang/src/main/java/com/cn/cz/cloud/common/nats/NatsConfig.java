package com.cn.cz.cloud.common.nats;

import com.google.inject.Inject;

/**
 * @author ywaz
 * @date 5/11/18 11:30
 */
public class NatsConfig {
    private String url = "nats://192.168.1.238:4222";
    private String subject = "nats_test";

    @Inject
    public NatsConfig() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
