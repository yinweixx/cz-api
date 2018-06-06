package com.cn.cz.cloud.common.etcd;

import com.cn.cz.cloud.common.exception.EtcdException;
import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.Lease;
import com.coreos.jetcd.Lock;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.kv.GetResponse;
import com.coreos.jetcd.kv.PutResponse;
import com.coreos.jetcd.lease.LeaseGrantResponse;
import com.coreos.jetcd.lock.LockResponse;
import com.coreos.jetcd.options.GetOption;
import com.coreos.jetcd.options.PutOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EtcdClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtcdClient.class);

    private static EtcdClient etcdClient;
    private static Client client;
    private static Lock lockClient;
    private static Lease lease;
    private static KV kvClient;

    private EtcdClient(){
        this.client = Client.builder().endpoints("http://192.168.1.205:2379").build();
        this.lockClient = client.getLockClient();
        this.lease = client.getLeaseClient();
        this.kvClient = client.getKVClient();
    }

    public static EtcdClient GetEtcdClient(){
        synchronized (EtcdClient.class){
            if ( etcdClient == null ){
                etcdClient = new EtcdClient();
            }
        }
        return etcdClient;
    }

    public EtcdClient getEtcdClient() {
        return etcdClient;
    }

    public void setEtcdClient(EtcdClient etcdClient) {
        this.etcdClient = etcdClient;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Lock getLockClient() {
        return lockClient;
    }

    public void setLockClient(Lock lockClient) {
        this.lockClient = lockClient;
    }

    public ByteSequence lockLeaseKey(String key, int time) throws com.coreos.jetcd.common.exception.EtcdException, ExecutionException, InterruptedException {
        long lease = granLease(time);
        CompletableFuture<LockResponse> feature = lockClient.lock(ByteSequence.fromString(key),lease);
        LockResponse response = feature.get();
        return response.getKey();
    }

    public long grantAndKeepAliveLease(long ttlSecond) throws EtcdException {
        long leaseId = granLease(ttlSecond);
        lease.keepAlive(leaseId);
        LOGGER.info("keep-alive etcd lease id [{}]", leaseId);
        return leaseId;
    }

    public long granLease(long ttlSecond) throws EtcdException {
        CompletableFuture<LeaseGrantResponse> grantFuture = lease.grant(ttlSecond);
        long leaseId;
        try {
            leaseId = grantFuture.get().getID();
        } catch (Exception e) {
            throw new EtcdException(e);
        }
        LOGGER.info("grant etcd lease id [{}]", leaseId);
        return leaseId;
    }

    public void putKeyValueByLease(long leaseId, String key, String value) throws EtcdException {
        ByteSequence testPutKey = ByteSequence.fromString(key);
        ByteSequence testPutValue = ByteSequence.fromString(value);
        PutOption option = PutOption.newBuilder().withLeaseId(leaseId).build();
        CompletableFuture<PutResponse> putFuture = kvClient.put(testPutKey, testPutValue, option);
        try {
            putFuture.get();
        } catch (Exception e) {
            throw new EtcdException(e);
        }
        LOGGER.debug("put key [{}] value [{}]", key, value);
    }

    public boolean isKeyExist(String key) throws EtcdException {
        ByteSequence keyByteSequence = ByteSequence.fromString(key);
        GetOption option = GetOption.newBuilder().withKeysOnly(true).build();
        try {
            GetResponse response = kvClient.get(keyByteSequence, option).get();
            List<KeyValue> keyValues = response.getKvs();
            if (keyValues == null || keyValues.size() == 0)
                return false;
            return true;
        } catch (Exception e) {
            throw new EtcdException(e);
        }
    }

    public List<KeyValue> getWithPrefix(String key, boolean keysOnly) throws EtcdException {
        ByteSequence keyByteSequence = ByteSequence.fromString(key);
        GetOption option = GetOption.newBuilder().withPrefix(keyByteSequence).withKeysOnly(keysOnly).build();
        try {
            return kvClient.get(keyByteSequence, option).get().getKvs();
        } catch (Exception e) {
            throw new EtcdException(e);
        }
    }

    public void unLockLeaseKey(ByteSequence key) throws EtcdException {
        try {
            lockClient.unlock(key).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
