package com.webculcate.eventservicecore.model.dto.venue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.webculcate.eventservicecore.constant.APIMetadata.VENUE_CREATION;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VenueCreationResponse {

    private String message;

    private VenueDto venue;

    public VenueCreationResponse(String message, VenueDto venue) {
        this.message = message;
        this.venue = venue;
    }

    public VenueCreationResponse(VenueDto venue) {
        this.venue = venue;
        this.message = VENUE_CREATION.getSuccessMessage();
    }
}
