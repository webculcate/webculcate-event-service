package com.webculcate.eventservicecore.service.venue.impl;

import com.webculcate.eventservicecore.model.dto.venue.VenueCreationRequest;
import com.webculcate.eventservicecore.model.dto.venue.VenueCreationResponse;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.repository.venue.VenueRepository;
import com.webculcate.eventservicecore.service.venue.IVenueDtoMapper;
import com.webculcate.eventservicecore.service.venue.IVenueService;
import com.webculcate.eventservicecore.service.venue.factory.IVenueFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.DEFAULT_VENUE_SERVICE;

@Slf4j
@Service(DEFAULT_VENUE_SERVICE)
@RequiredArgsConstructor
public class VenueService implements IVenueService {

    private final VenueRepository repository;

    private final IVenueFactory venueFactory;

    private final IVenueDtoMapper venueDtoMapper;

    @Override
    public VenueCreationResponse createVenue(VenueCreationRequest request) {
        Venue newVenue = venueFactory.generateVenue(request);
        Venue savedVenue = saveVenue(newVenue);
        return new VenueCreationResponse(venueDtoMapper.mapToVenueDto(savedVenue));
    }

    @Transactional
    private Venue saveVenue(Venue newVenue) {
        return repository.save(newVenue);
    }
}
