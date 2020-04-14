package com.github.mirowww.boot.exception;

import org.springframework.context.MessageSource;

public class HttpRequestException extends I18nException {

    private static final long serialVersionUID = 1L;
    private String key;

    public HttpRequestException(String code, Object... args) {
        this.key = code;
        this.setData(args);
    }

    public HttpRequestException(String message) {
        super(message);
    }

    public HttpRequestException(Exception ex, String message) {
        super(message, ex);
    }

    @Override
    public String i18n(MessageSource messageSource) {
        if (this.key == null) {
            return this.getMessage();
        }
        return this.i18n(messageSource, this.key, this.getData());
    }


}
