package com.cn.cz.cloud.common.nats.format;

import com.cn.cz.cloud.common.json.JsonUtil;
import com.cn.cz.cloud.common.nats.protocol.AbstractAnyunCloudMessageFormat;
import com.cn.cz.cloud.common.nats.protocol.ResponseMessage;

public class DefaultJsonMessageFormat extends AbstractAnyunCloudMessageFormat {
    @Override
    public byte[] format(String business, Object obj) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(buildHeader());
        responseMessage.setCode(200);
        ResponseMessage.RespResult result = buildResult(business, obj);
        responseMessage.setResult(result);
        String json = JsonUtil.toJson(responseMessage);
        return json.getBytes();
    }

    @Override
    public String getApplication() {
        return "container-controller";
    }
}