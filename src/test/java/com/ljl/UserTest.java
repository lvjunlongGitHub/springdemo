package com.ljl;

import com.ljl.entity.Channel;
import com.ljl.entity.Teacher;
import com.ljl.service.ChannelService;
import com.ljl.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lvjunlong
 * @date 2019/8/21 上午10:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserTest {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void testChannel() {
        Channel channel = channelService.getChannel(1);
        System.out.println(channel);
    }

    @Test
    public void testTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("yajun");
        teacher.setId(1164063292580790273l);
        //Boolean aBoolean = teacherService.saveTeacher(teacher);
        Boolean aBoolean = teacherService.updateTeacher(teacher);
        System.out.println(aBoolean + "===========================");
    }
}
