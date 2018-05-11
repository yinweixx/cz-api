package com.cn.cz.cloud.common.exception;

public class ProxyServiceMethodBuilderException extends ControllerException {
    public ProxyServiceMethodBuilderException() {
    }

    public ProxyServiceMethodBuilderException(String message) {
        super(message);
    }

    public ProxyServiceMethodBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxyServiceMethodBuilderException(Throwable cause) {
        super(cause);
    }

    public ProxyServiceMethodBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}