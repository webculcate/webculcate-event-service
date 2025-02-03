package com.webculcate.eventservicecore.exception.event;

import java.util.List;

public class InvalidEventCreationRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidEventCreationRequestException(List<String> messageList) {
        this.messageList = messageList;
    }
}
