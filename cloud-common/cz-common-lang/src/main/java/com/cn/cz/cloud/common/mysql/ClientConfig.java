package com.cn.cz.cloud.common.mysql;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;

/**
 * @author ywaz
 * @date 5/10/18 16:31
 */
@Singleton
public class ClientConfig implements Serializable{
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://cluster.mysql.anyuncloud.com/anyuncloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
    private String username = "root";
    private String password = "111111";

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EtcdConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
