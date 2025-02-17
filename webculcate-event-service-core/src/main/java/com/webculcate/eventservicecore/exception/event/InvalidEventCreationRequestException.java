package com.webculcate.eventservicecore.exception.event;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidEventCreationRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidEventCreationRequestException(List<String> messageList) {
        this.messageList = messageList;
    }
}
