package com.webculcate.eventservicecore.service.venue;

import com.webculcate.eventservicecore.model.dto.venue.VenueCreationRequest;
import com.webculcate.eventservicecore.model.dto.venue.VenueCreationResponse;

public interface IVenueService {

    VenueCreationResponse createVenue(VenueCreationRequest request);

}
