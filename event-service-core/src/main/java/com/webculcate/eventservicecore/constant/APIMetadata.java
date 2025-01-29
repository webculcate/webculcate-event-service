package com.webculcate.eventservicecore.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum APIMetadata {

    VENUE_CREATION("Venue creation successful", "Venue creation failed"),
    VENUE_MODIFICATION("Venue updated successfully", "Failed to update venue");

    private final String successMessage;

    private final String failureMessage;

}
