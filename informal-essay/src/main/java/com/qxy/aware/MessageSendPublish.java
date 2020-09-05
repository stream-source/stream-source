package com.qxy.aware;

import com.qxy.event.MessageSendEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 事件源：发布事件
 * multi caster广播事件--->listener监听事件---->publish事件源发布事件
 */
@Component
public class MessageSendPublish {
    @Autowired
    private ApplicationContext applicationContext;

    public void sendMessage(String targetMobile) {
        System.out.println("准备发送消息");
        MessageSendEvent sendEvent = new MessageSendEvent(applicationContext, targetMobile);
        applicationContext.publishEvent(sendEvent);
    }
}
