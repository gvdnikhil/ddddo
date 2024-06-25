package com.hashedin.huSpark.exception.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EventOverlapException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(EventOverlapException.class);

    public EventOverlapException(String message) {
        super(message);
        logger.info("Event overlap exception");

    }
}
