package com.cn.cz.cloud.common.exception;

/**
 * @author ywaz
 * @date 5/11/18 14:27
 */
public class EtcdException extends ControllerException{
    public EtcdException() {
    }

    public EtcdException(String message) {
        super(message);
    }

    public EtcdException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtcdException(Throwable cause) {
        super(cause);
    }

    public EtcdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
