package com.webculcate.eventservicecore.model.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private EventDto event;

    public EventCreationResponse(String message, EventDto event) {
        this.message = message;
        this.event = event;
    }

    public EventCreationResponse(EventDto event) {
        this.event = event;
        this.message = EVENT_CREATION.getSuccessMessage();
    }
}
