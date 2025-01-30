package com.webculcate.eventservicecore.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceExceptionType {

    // venue
    INVALID_VENUE_CREATION_REQUEST("Venue creation request invalid"),
    VENUE_NOT_AVAILABLE("Venue not found"),
    VENUE_ID_COUNT_EXCEEDED("Venue id count exceeded");

    private final String message;

}
