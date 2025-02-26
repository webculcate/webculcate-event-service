package com.webculcate.eventservicecore.exception.scheduledevent;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidCreateEventScheduleRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidCreateEventScheduleRequestException(String message) {
        super(message);
    }

    public InvalidCreateEventScheduleRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }

}
