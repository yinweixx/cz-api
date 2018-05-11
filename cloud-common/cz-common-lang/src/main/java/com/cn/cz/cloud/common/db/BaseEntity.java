package com.cn.cz.cloud.common.db;

import java.io.Serializable;

/**
 * @author ywaz
 * @date 5/11/18 11:15
 */
public interface BaseEntity<T> extends Serializable {
    String asJson();
    T build();
}
