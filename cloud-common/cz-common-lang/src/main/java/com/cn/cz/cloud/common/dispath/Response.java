package com.cn.cz.cloud.common.dispath;

import com.cn.cz.cloud.common.db.AbstractEntity;

/**
 * @author ywaz
 * @date 5/11/18 15:54
 */
public class Response<T> extends AbstractEntity {
    private T context;
    private int code;
    private String message;

    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
