package com.cn.cz.cloud.common.nats.format;

import com.cn.cz.cloud.common.nats.params.MessageFormater;
import com.cn.cz.cloud.common.nats.protocol.MessageHeader;
import com.cn.cz.cloud.common.nats.protocol.ResponseMessage;

public abstract class AbstractAnyunCloudMessageFormat implements MessageFormater {
    protected final  ResponseMessage.RespResult buildResult(String business, Object returnValue) {
        ResponseMessage.RespResult result = new ResponseMessage.RespResult();
        result.setContent(returnValue);
        result.setType(business);
        return result;
    }

    protected final MessageHeader buildHeader() {
        MessageHeader header = new MessageHeader();
        header.setApplication(getApplication());
        header.setTime(System.currentTimeMillis());
        header.setType(MessageHeader.TYPE_RESP);
        header.setVersion("1.0.0");
        return header;
    }
}