package com.cn.cz.cloud.common.nats.format;

import com.cn.cz.cloud.common.exception.ParamsBuilderException;

import java.util.List;
import java.util.Map;

/**
 * @author ywaz
 * @date 5/11/18 15:02
 */
public interface ParamsBuilder<T> {
    T build(Map<String, List<String>> params) throws ParamsBuilderException;
}
