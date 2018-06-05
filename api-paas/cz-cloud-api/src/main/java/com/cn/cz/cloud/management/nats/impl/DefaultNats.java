package com.cn.cz.cloud.management.nats.impl;

import com.cn.cz.cloud.common.exception.NatsInitialException;
import com.cn.cz.cloud.common.nats.Nats;
import com.cn.cz.cloud.common.nats.NatsConfig;
import com.cn.cz.cloud.management.nats.service.MessageProcessingService;
import com.google.inject.Inject;
import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author ywaz
 * @date 5/11/18 14:30
 */
public class DefaultNats implements Nats{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNats.class);
    private NatsConfig natsConfig;
    private ConnectionFactory connectionFactory;
    private Connection connection;
//    private MessageHandler messageHandler;

    @Inject
    public DefaultNats(NatsConfig natsConfig) {
        this.natsConfig = natsConfig;
//        this.messageHandler = messageHandler;
        connectionFactory = new ConnectionFactory(natsConfig.getUrl());
    }

    @Override
    public void connect() throws NatsInitialException {
        try {
            LOGGER.debug("connect nats [{}]", natsConfig.getUrl());
            connection = connectionFactory.createConnection();
        } catch (IOException e) {
            throw new NatsInitialException(e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    @Override
    public NatsConfig getNatsConfig() {
        return natsConfig;
    }

    @Override
    public void startMessageMonitor() {
        LOGGER.debug("Nats subscribe  subject: [{}]", natsConfig.getSubject());
        LOGGER.debug("Nats subscribe  url: [{}]", natsConfig.getUrl());
        if (connection == null){
            try {
                connection = connectionFactory.createConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new MessageProcessingService(connection, natsConfig.getSubject()).start();
    }
}
