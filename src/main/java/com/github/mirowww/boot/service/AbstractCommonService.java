package com.github.mirowww.boot.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractCommonService implements ApplicationContextAware {

    protected static final ObjectMapper mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    protected ApplicationContext applicationContext;

    @PersistenceContext
    EntityManager manager;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    void publishEvent(ApplicationEvent event) {
        this.applicationContext.publishEvent(event);
    }

}
