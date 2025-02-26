package com.webculcate.eventservicecore.exception.scheduledevent;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidUpdateEventScheduleRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidUpdateEventScheduleRequestException(String message) {
        super(message);
    }

    public InvalidUpdateEventScheduleRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }

}
