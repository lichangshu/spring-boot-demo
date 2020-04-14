package com.github.mirowww.boot.exception;

import org.springframework.context.MessageSource;

public abstract class LevelLowException {

    public static class UserLevelLowException extends ServiceException {
        private static final long serialVersionUID = 1L;

        public UserLevelLowException(Object data, String format, Object... args) {
            super(data, format, args);
        }

        @Override
        public String i18n(MessageSource messageSource) {
            return this.i18n(messageSource, "user.level.too.low", this.getData());
        }
    }

    public static class BuildingLevelLowException extends ServiceException {
        private static final long serialVersionUID = 1L;

        public BuildingLevelLowException(Object data, String format, Object... args) {
            super(data, format, args);
        }

        @Override
        public String i18n(MessageSource messageSource) {
            return this.i18n(messageSource, "user.building.level.too.low", this.getData());
        }
    }
}
