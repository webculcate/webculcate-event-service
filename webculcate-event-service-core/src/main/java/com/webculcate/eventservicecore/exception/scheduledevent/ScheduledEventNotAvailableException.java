package com.webculcate.eventservicecore.exception.scheduledevent;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduledEventNotAvailableException extends RuntimeException {

    private List<String> messageList;

    public ScheduledEventNotAvailableException(List<String> messageList) {
        this.messageList = messageList;
    }

    public ScheduledEventNotAvailableException(String message) {
        super(message);
    }

}
