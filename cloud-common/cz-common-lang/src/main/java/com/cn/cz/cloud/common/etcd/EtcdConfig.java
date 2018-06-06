package com.cn.cz.cloud.common.etcd;

import com.google.inject.Inject;

import javax.inject.Singleton;
import java.io.Serializable;

/**
 * @author ywaz
 * @date 5/10/18 16:43
 */
@Singleton
public class EtcdConfig implements Serializable{
    private String schema;
    private String host;
    private String port;
    private String basePathSegment;

    @Inject
    public EtcdConfig() {
        this.schema = "http";
        this.host = "http://etcd_server.service.consul:2379";
        this.port = "2379";
        this.basePathSegment = "v3";
    }

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

    public String getBasePathSegment() {
        return basePathSegment;
    }

    public void setBasePathSegment(String basePathSegment) {
        this.basePathSegment = basePathSegment;
    }

    @Override
    public String toString() {
        return "EtcdConfig{" +
                "schema='" + schema + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", basePathSegment='" + basePathSegment + '\'' +
                '}';
    }
}
