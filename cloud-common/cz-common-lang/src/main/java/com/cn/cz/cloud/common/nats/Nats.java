package com.cn.cz.cloud.common.nats;

import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;
import io.nats.client.NATSException;

/**
 * @author ywaz
 * @date 5/11/18 13:33
 */
public interface Nats {
    void connect() throws NATSException;

    Connection getConnection();

    ConnectionFactory getConnectionFactory();

    NatsConfig getNatsConfig();

    void startMessageMonitor();
}
