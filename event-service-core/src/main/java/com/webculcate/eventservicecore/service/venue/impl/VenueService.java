package com.webculcate.eventservicecore.service.venue.impl;

import com.webculcate.eventservicecore.exception.venue.VenueNotAvailableException;
import com.webculcate.eventservicecore.model.dto.venue.*;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.repository.venue.VenueRepository;
import com.webculcate.eventservicecore.service.venue.IVenueDtoMapper;
import com.webculcate.eventservicecore.service.venue.IVenueService;
import com.webculcate.eventservicecore.service.venue.factory.IVenueFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.VENUE_NOT_AVAILABLE;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.DEFAULT_VENUE_SERVICE;

@Slf4j
@Service(DEFAULT_VENUE_SERVICE)
@RequiredArgsConstructor
public class VenueService implements IVenueService {

    private final VenueRepository repository;

    private final IVenueFactory venueFactory;

    private final IVenueDtoMapper venueDtoMapper;

    @Override
    public VenueDto getVenue(Long id) {
        Optional<Venue> optionalVenue = fetchVenue(id);
        Venue fetchedVenue = optionalVenue
                .orElseThrow(() -> new VenueNotAvailableException(VENUE_NOT_AVAILABLE.getMessage()));
        return venueDtoMapper.mapToVenueDto(fetchedVenue);
    }

    @Transactional
    private Optional<Venue> fetchVenue(Long id) {
        return repository.findByVenueId(id);
    }

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

    @Override
    public VenueUpdateResponse updateVenue(VenueUpdateRequest request) {
        Venue updatedVenue = venueFactory.generateVenue(request);
        Venue savedVenue = fetchAndUpdateVenue(request.getVenueId(), updatedVenue);
        return new VenueUpdateResponse(venueDtoMapper.mapToVenueDto(savedVenue));
    }

    @Transactional
    private Venue fetchAndUpdateVenue(Long venueId, Venue updatedVenue) {
        Optional<Venue> optionalVenue = fetchVenue(venueId);
        Venue fetchedVenue = optionalVenue
                .orElseThrow(() -> new VenueNotAvailableException(VENUE_NOT_AVAILABLE.getMessage()));
        fetchedVenue.setVenueName(updatedVenue.getVenueName());
        fetchedVenue.setVenueAddress(updatedVenue.getVenueAddress());
        Venue savedVenue = saveVenue(fetchedVenue);
        return savedVenue;
    }
}
