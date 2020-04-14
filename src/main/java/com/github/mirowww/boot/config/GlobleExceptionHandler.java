package com.github.mirowww.boot.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mirowww.boot.controller.BasicController;
import com.github.mirowww.boot.domain.User;
import com.github.mirowww.boot.exception.I18nException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobleExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobleExceptionHandler.class);
    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(value = {I18nException.class})
    @ResponseBody
    public ExceptionModel requestException(HttpServletRequest req, HttpServletResponse resp, I18nException ex)
            throws IOException {
        User user = BasicController.local.get();
        if (user != null) {
            Class<?> clazz = ex.getClass();
            if (ex.getCause() != null) {
                clazz = ex.getCause().getClass();
            }
            log.warn("Request I18N error {}, User [{},{}], message : {}", //
                    clazz.getSimpleName(), user.getId(), user.getOpenid(), ex.getMessage());
        } else {
            log.warn("Request I18N error: {}", ex.getMessage(), ex);
        }
        ExceptionModel model = new ExceptionModel().setError("BAD_REQUEST")//
                .setMessage(ex.i18n(messageSource))//
                .setPath(req.getRequestURI())//
                .setStatus(400)//
                .setTimestamp(System.currentTimeMillis());
        resp.setStatus(model.getStatus());
        if (ex.getCause() != null) {
            return model.setException(ex.getCause().getClass().getSimpleName());
        }
        return model.setException(ex.getClass().getSimpleName());
    }

    public static class ExceptionModel {
        private long timestamp;
        private int status;
        private String error;
        private String exception;
        private String message;
        private String path;

        public long getTimestamp() {
            return timestamp;
        }

        public ExceptionModel setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public int getStatus() {
            return status;
        }

        public ExceptionModel setStatus(int status) {
            this.status = status;
            return this;
        }

        public String getError() {
            return error;
        }

        public ExceptionModel setError(String error) {
            this.error = error;
            return this;
        }

        public String getException() {
            return exception;
        }

        public ExceptionModel setException(String exception) {
            this.exception = exception;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public ExceptionModel setMessage(String message) {
            this.message = message;
            return this;
        }

        public String getPath() {
            return path;
        }

        public ExceptionModel setPath(String path) {
            this.path = path;
            return this;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
