package com.webculcate.eventservicecore.exception.venue;

import lombok.Getter;

import java.util.List;

@Getter
public class VenueNotAvailableException extends RuntimeException {

    private List<String> messageList;

    public VenueNotAvailableException(List<String> messageList) {
        this.messageList = messageList;
    }

}
