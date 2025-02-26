package com.webculcate.eventservicecore.exception.venue;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidVenueCreationRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidVenueCreationRequestException(String message) {
        super(message);
    }

    public InvalidVenueCreationRequestException(List<String> messageList) {
        this.messageList = messageList;
    }

}
