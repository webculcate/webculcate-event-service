package com.webculcate.eventservicecore.service.venue.factory;

import com.webculcate.eventservicecore.model.dto.venue.VenueCreationRequest;
import com.webculcate.eventservicecore.model.entity.Venue;

public interface IVenueFactory {

    Venue generateVenue(VenueCreationRequest request);

}
