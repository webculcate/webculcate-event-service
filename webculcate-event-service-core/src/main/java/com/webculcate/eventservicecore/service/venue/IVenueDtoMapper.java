package com.webculcate.eventservicecore.service.venue;

import com.webculcate.eventservicecore.model.dto.venue.VenueDto;
import com.webculcate.eventservicecore.model.entity.Venue;

public interface IVenueDtoMapper {
    VenueDto mapToVenueDto(Venue venue);
}
