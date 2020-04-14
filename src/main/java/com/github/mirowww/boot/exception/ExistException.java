package com.github.mirowww.boot.exception;

import org.springframework.context.MessageSource;

public abstract class ExistException {

    public static class UserNoExistException extends ServiceException {

        private static final long serialVersionUID = 1L;

        public UserNoExistException(Object data, String format, Object... args) {
            super(data, format, args);
        }

        @Override
        public String i18n(MessageSource messageSource) {
            return this.i18n(messageSource, "user.is.not.exist", this.getData());
        }

    }
}
