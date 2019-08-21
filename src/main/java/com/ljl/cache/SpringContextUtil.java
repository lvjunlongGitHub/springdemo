package com.ljl.cache;

import com.ljl.logging.SystemLogger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午2:57
 */
@Component
@Scope("singleton")
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获得spring上下文
     * @return ApplicationContext spring上下文
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 获取bean
     * @param name service注解方式name为小驼峰格式
     * @return  Object bean的实例对象
     */
    static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 获取bean
     * @param name    bean名称
     * @param clazz   范型
     * @param <T>     T
     * @return        T
     */
    public static <T> T getBeanByClass(String name,Class<T> clazz) throws BeansException {
        return applicationContext.getBean(name,clazz);
    }

    @PreDestroy
    public void onDestroy(){
        SystemLogger.info("Now to clean ...");
    }
}
