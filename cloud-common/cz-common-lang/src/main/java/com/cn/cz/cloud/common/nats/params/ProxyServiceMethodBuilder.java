package com.cn.cz.cloud.common.nats.params;

import com.cn.cz.cloud.common.exception.ProxyServiceMethodBuilderException;
import com.google.inject.Binding;
import com.google.inject.Key;

import java.util.List;
import java.util.Map;

public interface ProxyServiceMethodBuilder {
    ProxyServiceMethodInstance build(Map<Key<?>, Binding<?>> bindings, String business, Map<String, List<String>> paramsMap)
            throws ProxyServiceMethodBuilderException;
}