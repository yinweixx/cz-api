package com.cn.cz.cloud.common.etcd;

import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.exception.EtcdException;

import java.util.List;

/**
 * @author ywaz
 * @date 5/11/18 13:30
 */
public interface Etcd {

    long grantAndKeepAliveLease(long ttlSecond) throws EtcdException;

    long granLease(long ttlSecond) throws EtcdException;

    void putKeyValueByLease(long leaseId,String key,String value) throws EtcdException;

    boolean isKeyExist(String key) throws EtcdException;

    List<KeyValue> getWithPrefix(String key, boolean keysOnly) throws EtcdException;
}
