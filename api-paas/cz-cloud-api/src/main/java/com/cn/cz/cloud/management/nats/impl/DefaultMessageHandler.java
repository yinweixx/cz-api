package com.cn.cz.cloud.management.nats.impl;

import com.cn.cz.cloud.common.base.Context;
import com.cn.cz.cloud.common.bean.InjectorsBuilder;
import com.cn.cz.cloud.common.json.JsonUtil;
import com.cn.cz.cloud.common.nats.format.ParamsBuilder;
import com.cn.cz.cloud.common.nats.params.MessageFormater;
import com.cn.cz.cloud.common.nats.params.ProxyServiceMethodBuilder;
import com.cn.cz.cloud.common.nats.params.ProxyServiceMethodInstance;
import com.cn.cz.cloud.common.nats.format.DefaultErrorMessageFormat;
import com.cn.cz.cloud.common.nats.protocol.RequestMessage;
import com.iciql.util.StringUtils;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ywaz
 * @date 5/11/18 15:00
 */
public class DefaultMessageHandler implements MessageHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageHandler.class);
    private Map<Class<? extends ParamsBuilder>, ParamsBuilder> builders;
    private ProxyServiceMethodBuilder proxyServiceMethodBuilder;
    private Context context;

    @Inject
    public DefaultMessageHandler(ProxyServiceMethodBuilder proxyServiceMethodBuilder, Context context) {
        this.context = context;
        builders = new HashMap<>();
        this.proxyServiceMethodBuilder = proxyServiceMethodBuilder;
    }

    @Override
    public void onMessage(Message message) {
        String recivedMessage = new String(message.getData());
        LOGGER.debug("recived message: {}", recivedMessage);
        String _business = "";
        try {
            RequestMessage<Map<String, List<String>>> requestMessage =
                    JsonUtil.fromJson(RequestMessage.class, recivedMessage);
            String business = _business = requestMessage.getBusiness();
            ProxyServiceMethodInstance<Map<String, List<String>>> instance =
                    proxyServiceMethodBuilder.build(
                            InjectorsBuilder.getBuilder().getKernelInjector().getBindings(),
                            business, requestMessage.getContent());
            if (instance == null) {
                throw new Exception("not find service by [" + business + "]");
            }
            Object invokeValue = instance.invoke();
            if (StringUtils.isNullOrEmpty(message.getReplyTo())) {
                LOGGER.debug("could not find reply to inbox");
                return;
            }
            MessageFormater formater = instance.getFormater();
            Message response = new Message();
            response.setSubject(message.getReplyTo());
            response.setData(formater.format(business, invokeValue));
            LOGGER.debug("response: {}", new String(response.getData()));
//            context.getNats().getConnection().publish(response);
        } catch (Exception ex) {
            if (StringUtils.isNullOrEmpty(message.getReplyTo())) {
                LOGGER.debug("could not find reply to inbox");
                return;
            }
            MessageFormater formater = new DefaultErrorMessageFormat();
            byte[] error = formater.format(_business, ex);
            Message response = new Message(message.getReplyTo(), null, error);

//            try {
//                context.getNats().getConnection().publish(response);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        String replyTo = message.getReplyTo();
        if (StringUtils.isNullOrEmpty(replyTo))
            return;
    }
}
