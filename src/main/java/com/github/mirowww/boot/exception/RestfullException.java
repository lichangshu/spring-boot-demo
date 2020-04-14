package com.github.mirowww.boot.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class RestfullException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(RestfullException.class);
    private static final long serialVersionUID = 1L;
    public static final ObjectMapper mapper = new ObjectMapper();

    RestMessage restMessage;

    public RestfullException() {
    }

    public RestfullException(RestMessage restMessage) {
        this.restMessage = restMessage;
    }

    public static RestfullException exception(InputStream input)
            throws JsonParseException, JsonMappingException, IOException {
        RestMessage message = mapper.readValue(input, RestMessage.class);
        log.error("Request Error ! {}", message);
        return new RestfullException(message);
    }

    public RestMessage getRestMessage() {
        return restMessage;
    }

    public void setRestMessage(RestMessage restMessage) {
        this.restMessage = restMessage;
    }

    public static class RestMessage {
        private long timestamp;
        private int status;
        private String error;
        private String exception;
        private String message;
        private String path;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

    }
}
