package com.qxy.listener;

import com.qxy.event.MessageSendEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MessageSendListener implements ApplicationListener<MessageSendEvent> {
    @Override
    public void onApplicationEvent(MessageSendEvent event) {
        System.out.println("监听到目标手机："  + event.getTargetMobile() + "收到消息!");
    }
}
