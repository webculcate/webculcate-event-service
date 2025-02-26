package com.webculcate.eventservicecore.service.venue.factory.impl;

import com.webculcate.eventservicecore.model.dto.venue.VenueCreationRequest;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import com.webculcate.eventservicecore.service.venue.factory.IVenueFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultVenueFactory implements IVenueFactory {

    @Override
    public Venue generateVenue(VenueCreationRequest request) {
        return Venue.builder()
                .venueName(request.getVenueName())
                .venueAddress(request.getVenueAddress())
                .timeLog(new TimeLog())
                .build();
    }

}
