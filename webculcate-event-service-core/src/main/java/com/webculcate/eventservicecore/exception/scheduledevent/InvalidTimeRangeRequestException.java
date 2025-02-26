package com.webculcate.eventservicecore.exception.scheduledevent;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidTimeRangeRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidTimeRangeRequestException(String message) {
        super(message);
    }

    public InvalidTimeRangeRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }

}
