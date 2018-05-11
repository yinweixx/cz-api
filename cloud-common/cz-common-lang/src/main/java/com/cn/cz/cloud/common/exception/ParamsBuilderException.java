package com.cn.cz.cloud.common.exception;

/**
 * @author ywaz
 * @date 5/11/18 15:02
 */
public class ParamsBuilderException extends ControllerException{
    public ParamsBuilderException() {
    }

    public ParamsBuilderException(String message) {
        super(message);
    }

    public ParamsBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsBuilderException(Throwable cause) {
        super(cause);
    }

    public ParamsBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
