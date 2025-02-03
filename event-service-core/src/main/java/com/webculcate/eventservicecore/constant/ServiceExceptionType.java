package com.webculcate.eventservicecore.constant;

import com.webculcate.eventservicecore.model.entity.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceExceptionType {

    // venue
    INVALID_VENUE_CREATION_REQUEST("Venue creation request invalid"),
    VENUE_NOT_AVAILABLE("Venue not found"),
    VENUE_ID_COUNT_EXCEEDED("Venue id count exceeded"),

    // event
    INVALID_EVENT_CREATION_REQUEST("Event creation request invalid"),
    EVENT_NOT_AVAILABLE("Event not found"),
    EVENT_ID_COUNT_EXCEEDED("Event id count exceeded");

    private final String message;

}
