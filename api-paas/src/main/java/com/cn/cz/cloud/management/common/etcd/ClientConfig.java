package com.cn.cz.cloud.management.common.etcd;

import javax.inject.Singleton;
import java.io.Serializable;

/**
 * @author ywaz
 * @date 5/10/18 16:43
 */
@Singleton
public class ClientConfig implements Serializable{
    private String schema = "http";
    private String host = "etcd";
    private int port = 2379;
    private String basePathSegment = "v3";

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getBasePathSegment() {
        return basePathSegment;
    }

    public void setBasePathSegment(String basePathSegment) {
        this.basePathSegment = basePathSegment;
    }

    @Override
    public String toString() {
        return "ClientConfig{" +
                "schema='" + schema + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", basePathSegment='" + basePathSegment + '\'' +
                '}';
    }
}
