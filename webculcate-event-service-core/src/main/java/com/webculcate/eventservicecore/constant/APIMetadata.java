package com.webculcate.eventservicecore.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum APIMetadata {

    VENUE_CREATION("Venue creation successful", "Venue creation failed"),
    VENUE_MODIFICATION("Venue updated successfully", "Failed to update venue"),
    EVENT_CREATION("Event creation successful", "Event creation failed"),
    EVENT_MODIFICATION("Event updated successfully", "Failed to update event"),
    SCHEDULED_EVENT_CREATION("Event schedule creation successful", "Event schedule creation failed"),
    SCHEDULED_EVENT_MODIFICATION("Event schedule modification successful", "Failed to update event schedule");

    private final String successMessage;

    private final String failureMessage;

}
