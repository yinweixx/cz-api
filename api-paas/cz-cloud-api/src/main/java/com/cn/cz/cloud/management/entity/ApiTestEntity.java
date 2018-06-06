package com.cn.cz.cloud.management.entity;

import com.cn.cz.cloud.common.db.AbstractEntity;
import com.iciql.Iciql;

/**
 * @author ywaz
 * @date 5/11/18 16:01
 */

@Iciql.IQTable(name = "test")
public class ApiTestEntity extends AbstractEntity{

    @Iciql.IQColumn(name = "id",primaryKey = true,autoIncrement = true)
    public Integer id;

    @Iciql.IQColumn(name = "url",length = 200)
    public String url;

    @Iciql.IQColumn(name = "lanm",length = 200)
    public String lanm;

    @Iciql.IQColumn(name = "num")
    public Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanm() {
        return lanm;
    }

    public void setLanm(String lanm) {
        this.lanm = lanm;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
