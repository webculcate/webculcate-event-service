package com.webculcate.eventservicecore.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceExceptionType {

    // venue
    INVALID_VENUE_CREATION_REQUEST("Venue creation request invalid"),
    INVALID_VENUE_UPDATE_REQUEST("Venue update request invalid"),
    VENUE_NOT_AVAILABLE("Venue not found"),
    VENUE_ID_COUNT_EXCEEDED("Venue id count exceeded"),

    // event
    INVALID_EVENT_CREATION_REQUEST("Event creation request invalid"),
    INVALID_EVENT_UPDATE_REQUEST("Event update request invalid"),
    EVENT_NOT_AVAILABLE("Event not found"),
    EVENT_ID_COUNT_EXCEEDED("Event id count exceeded"),

    // scheduled event
    INVALID_EVENT_SCHEDULE_CREATION_REQUEST("Event schedule creation request invalid"),
    INVALID_EVENT_SCHEDULE_UPDATE_REQUEST("Event schedule update request invalid"),
    INVALID_CAPACITY_UPDATE_REQUEST("Capacity update request invalid"),
    INVALID_TIME_RANGE_REQUEST("Time range invalid"),
    SCHEDULED_EVENT_NOT_AVAILABLE("Scheduled event not found");

    private final String message;

}
