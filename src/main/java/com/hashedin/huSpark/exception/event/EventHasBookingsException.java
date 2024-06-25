package com.hashedin.huSpark.exception.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EventHasBookingsException extends RuntimeException {
    public EventHasBookingsException(String message) {
        super(message);
    }
}