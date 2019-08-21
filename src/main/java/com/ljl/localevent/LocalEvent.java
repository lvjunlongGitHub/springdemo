package com.ljl.localevent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午3:26
 */
@Component
public class LocalEvent {

    private ApplicationEventPublisher publisher;
    private String topic;
    private Object content;

    public LocalEvent() {
    }

    public static LocalEvent builder(ApplicationEventPublisher publisher) {
        LocalEvent localEvent = new LocalEvent();
        localEvent.setPublisher(publisher);
        return localEvent;
    }

    public LocalEvent setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public LocalEvent setContent(Object content) {
        this.content = content;
        return this;
    }

    private void setPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish() {
        this.publisher.publishEvent(this);
    }

    public String getTopic() {
        return this.topic;
    }

    public Object getContent() {
        return this.content;
    }
}
