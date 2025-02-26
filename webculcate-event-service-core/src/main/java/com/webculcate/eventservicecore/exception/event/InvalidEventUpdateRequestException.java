package com.webculcate.eventservicecore.exception.event;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidEventUpdateRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidEventUpdateRequestException(String message) {
        super(message);
    }

    public InvalidEventUpdateRequestException(List<String> messageList) {
        this.messageList = messageList;
    }

}
