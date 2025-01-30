package com.webculcate.eventservicecore.model.dto.venue;

import static com.webculcate.eventservicecore.constant.APIMetadata.VENUE_MODIFICATION;

public class VenueUpdateResponse extends VenueCreationResponse {

    public VenueUpdateResponse(VenueDto venue) {
        super(VENUE_MODIFICATION.getSuccessMessage(), venue);
    }

}
