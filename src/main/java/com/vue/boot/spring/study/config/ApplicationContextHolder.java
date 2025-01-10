package com.vue.boot.spring.study.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 解决多线程bean注入null
 *
 * @author dufa
 * @date 2020-12-14
 */
@Slf4j
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {

        if (applicationContext == null) {
            log.info("applicationContext为空");
        }
        return (T) applicationContext.getBean(name);
    }

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
}