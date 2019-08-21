package com.ljl.handler.listener;

import com.ljl.entity.Teacher;
import com.ljl.localevent.LocalEvent;
import com.ljl.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午3:25
 */
@Component
public class LocalEventListener {

    @Autowired
    private TeacherService teacherService;


    /**
     * 通知cp发货
     * @param event
     */
    @Async
    @EventListener(condition = "#event.topic==T(com.eusdk.constant.LocalTopic).TOPIC_SEND_ORDER_CP")
    public void sendCallbackToServer(LocalEvent event) {
        Teacher msg = (Teacher) event.getContent();
        if (null != msg) {
            teacherService.saveTeacher(msg);
        }
    }
}
