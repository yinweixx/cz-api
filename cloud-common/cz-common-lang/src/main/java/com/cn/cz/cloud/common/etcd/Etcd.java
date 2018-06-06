package com.cn.cz.cloud.common.etcd;

import com.coreos.jetcd.common.exception.EtcdException;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author ywaz
 * @date 5/11/18 13:30
 */
public interface Etcd {

    ByteSequence lockLeaseKey(String key, int time) throws EtcdException, ExecutionException, InterruptedException;

    long grantAndKeepAliveLease(long ttlSecond) throws EtcdException;

    long granLease(long ttlSecond) throws EtcdException;

    void putKeyValueByLease(long leaseId,String key,String value) throws EtcdException;

    boolean isKeyExist(String key) throws EtcdException;

    List<KeyValue> getWithPrefix(String key, boolean keysOnly) throws EtcdException;

    void unLockLeaseKey(ByteSequence key) throws com.cn.cz.cloud.common.exception.EtcdException;
}
