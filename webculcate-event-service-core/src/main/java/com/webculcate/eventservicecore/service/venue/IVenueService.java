package com.webculcate.eventservicecore.service.venue;

import com.webculcate.eventservicecore.model.dto.venue.*;

public interface IVenueService {

    VenueCreationResponse createVenue(VenueCreationRequest request);

    VenueDto getVenue(Long id);

    VenueUpdateResponse updateVenue(VenueUpdateRequest request);
}
