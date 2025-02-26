package com.webculcate.eventservicecore.exception.venue;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidVenueUpdateRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidVenueUpdateRequestException(String message) {
        super(message);
    }

    public InvalidVenueUpdateRequestException(List<String> messageList) {
        this.messageList = messageList;
    }

}
