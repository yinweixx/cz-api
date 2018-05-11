package com.cn.cz.cloud.common.nats;

/**
 * @author ywaz
 * @date 5/11/18 11:30
 */
public class NatsConfig {
    private String url = "test";
    private String subject = "test";

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
