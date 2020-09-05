package com.qxy.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;


public class MessageSendEvent extends ApplicationContextEvent {
    private String targetMobile;
    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public MessageSendEvent(ApplicationContext source, String targetMobile) {
        super(source);
        this.targetMobile =targetMobile;
    }

    public String getTargetMobile() {
        return targetMobile;
    }
}
