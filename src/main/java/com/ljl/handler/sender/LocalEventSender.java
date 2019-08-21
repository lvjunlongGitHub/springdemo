package com.ljl.handler.sender;

import com.ljl.constant.LocalTopic;
import com.ljl.entity.Teacher;
import com.ljl.localevent.LocalEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午3:30
 */
@Component
public class LocalEventSender {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 异步通知cp服务器
     *
     * @param teacher 消息
     */
    public void asyncSendCallbackToServer(Teacher teacher) {
        LocalEvent.builder(applicationEventPublisher)
                .setTopic(LocalTopic.TOPIC_SAVE_TEACHER)
                .setContent(teacher)
                .publish();
    }
}
