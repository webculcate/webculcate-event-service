package com.webculcate.eventservicecore.service.venue.impl;

import com.webculcate.eventservicecore.exception.venue.VenueNotAvailableException;
import com.webculcate.eventservicecore.model.dto.venue.*;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.service.venue.IVenueDtoMapper;
import com.webculcate.eventservicecore.service.venue.IVenueService;
import com.webculcate.eventservicecore.service.venue.factory.IVenueFactory;
import com.webculcate.eventservicecore.service.venue.transaction.VenueTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.VENUE_NOT_AVAILABLE;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.DEFAULT_VENUE_SERVICE;

@Slf4j
@Service(DEFAULT_VENUE_SERVICE)
@RequiredArgsConstructor
public class VenueService implements IVenueService {

    private final VenueTransactionService venueTransactionService;

    private final IVenueFactory venueFactory;

    private final IVenueDtoMapper venueDtoMapper;

    @Override
    public VenueDto getVenue(Long id) {
        Optional<Venue> optionalVenue = venueTransactionService.fetchVenue(id);
        Venue fetchedVenue = optionalVenue
                .orElseThrow(() -> new VenueNotAvailableException(VENUE_NOT_AVAILABLE.getMessage()));
        log.info("Venue fetched");
        return venueDtoMapper.mapToVenueDto(fetchedVenue);
    }

    @Override
    public VenueCreationResponse createVenue(VenueCreationRequest request) {
        Venue newVenue = venueFactory.generateVenue(request);
        Venue savedVenue = venueTransactionService.saveVenue(newVenue);
        log.info("Venue created");
        return new VenueCreationResponse(venueDtoMapper.mapToVenueDto(savedVenue));
    }

    @Override
    public VenueUpdateResponse updateVenue(VenueUpdateRequest request) {
        Venue updatedVenue = venueFactory.generateVenue(request);
        Venue savedVenue = venueTransactionService.fetchAndUpdateVenue(request.getVenueId(), updatedVenue);
        log.info("Venue updated");
        return new VenueUpdateResponse(venueDtoMapper.mapToVenueDto(savedVenue));
    }

}
