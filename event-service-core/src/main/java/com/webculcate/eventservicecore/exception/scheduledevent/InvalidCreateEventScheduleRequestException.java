package com.webculcate.eventservicecore.exception.scheduledevent;

import java.util.List;

public class InvalidCreateEventScheduleRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidCreateEventScheduleRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }

}
