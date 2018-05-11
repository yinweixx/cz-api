package com.cn.cz.cloud.common.exception;

/**
 * @author ywaz
 * @date 5/11/18 14:32
 */
public class NatsInitialException extends ControllerException{
    public NatsInitialException() {
    }

    public NatsInitialException(String message) {
        super(message);
    }

    public NatsInitialException(String message, Throwable cause) {
        super(message, cause);
    }

    public NatsInitialException(Throwable cause) {
        super(cause);
    }

    public NatsInitialException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
