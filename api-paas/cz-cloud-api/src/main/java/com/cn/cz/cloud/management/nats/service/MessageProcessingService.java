package com.cn.cz.cloud.management.nats.service;

import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.common.dispath.ErrorCode;
import com.cn.cz.cloud.common.dispath.Response;
import com.cn.cz.cloud.management.dao.ApiTestDao;
import com.cn.cz.cloud.management.entity.ApiTestEntity;
import com.cn.cz.cloud.management.service.ApiTestService;
import com.cn.cz.cloud.service.params.BUSINESS_ENUM;
import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ywaz
 * @date 5/11/18 14:34
 */
public class MessageProcessingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageProcessingService.class);
    private Connection connect;
    private String subject;

    private static ApiTestService apiTestService = InjectorsBuilder.getBuilder().getInstanceByType(ApiTestService.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public MessageProcessingService(Connection connect, String subject) {
        this.connect = connect;
        this.subject = subject;
    }

    private void replyMessage(String reply, byte[] data) {
        try {
            connect.publish(reply, data);
        } catch (IOException e) {
            LOGGER.debug("IOException:[{}]", e);
        }
    }

    List<String> getEnumList() {
        List<String> list = null;
        BUSINESS_ENUM[] businessEnums = BUSINESS_ENUM.values();
        if (businessEnums != null && businessEnums.length > 0) {
            list = new LinkedList<>();
            for (BUSINESS_ENUM business_enum : businessEnums) {
                list.add(business_enum.name());
            }
        }
        return list;
    }

    public void start() {
        connect.subscribe(subject,"job", msg -> {
            executorService.submit(()->{
                LOGGER.debug("Message msg:[{}]", msg);

                apiTestService.queryDetails();

                try {
                    connect.publish(msg.getReplyTo(),"i get your help".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}