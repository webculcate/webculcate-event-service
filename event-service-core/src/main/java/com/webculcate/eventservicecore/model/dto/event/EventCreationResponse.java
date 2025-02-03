package com.webculcate.eventservicecore.model.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.eventservicecore.model.dto.venue.VenueDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.webculcate.eventservicecore.constant.APIMetadata.EVENT_CREATION;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventCreationResponse {

    private String message;

    private EventDto venue;

    public EventCreationResponse(String message, EventDto venue) {
        this.message = message;
        this.venue = venue;
    }

    public EventCreationResponse(EventDto venue) {
        this.venue = venue;
        this.message = EVENT_CREATION.getSuccessMessage();
    }
}
