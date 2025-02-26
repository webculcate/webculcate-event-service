package com.webculcate.eventservicecore.exception.scheduledevent;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidCapacityUpdateRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidCapacityUpdateRequestException(String message) {
        super(message);
    }

    public InvalidCapacityUpdateRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }

}
