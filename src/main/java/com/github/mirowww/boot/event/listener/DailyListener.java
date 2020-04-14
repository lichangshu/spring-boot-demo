package com.github.mirowww.boot.event.listener;

import com.github.mirowww.boot.event.DailyEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DailyListener {

    private static final Logger log = LoggerFactory.getLogger(DailyListener.class);

    @EventListener
    @Transactional(propagation = Propagation.MANDATORY)
    public void dailyRank(DailyEvents.DemoEvent event) {

    }

}
