package com.github.mirowww.boot.event;

import org.springframework.context.ApplicationEvent;

public class DailyEvents {

    public static class DemoEvent extends ApplicationEvent {
        private static final long serialVersionUID = 1L;

        public DemoEvent(Object source) {
            super(source);
        }
    }

}
