package com.webculcate.eventservicecore.exception.event;

import lombok.Getter;

import java.util.List;

@Getter
public class EventNotAvailableException extends RuntimeException {

    private List<String> messageList;

    public EventNotAvailableException(List<String> messageList) {
        this.messageList = messageList;
    }

    public EventNotAvailableException(String message) {
        super(message);
    }
}
