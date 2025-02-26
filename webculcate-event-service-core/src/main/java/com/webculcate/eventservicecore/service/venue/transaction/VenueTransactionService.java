package com.webculcate.eventservicecore.service.venue.transaction;

import com.webculcate.eventservicecore.exception.venue.VenueNotAvailableException;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.repository.venue.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.webculcate.eventservicecore.constant.ServiceExceptionType.VENUE_NOT_AVAILABLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class VenueTransactionService {

    private final VenueRepository repository;

    @Transactional(readOnly = true)
    public Optional<Venue> fetchVenue(Long id) {
        return repository.findByVenueId(id);
    }

    @Transactional
    public Venue saveVenue(Venue newVenue) {
        return repository.save(newVenue);
    }

    @Transactional
    public Venue fetchAndUpdateVenue(Long venueId, Venue updatedVenue) {
        Optional<Venue> optionalVenue = fetchVenue(venueId);
        Venue fetchedVenue = optionalVenue
                .orElseThrow(() -> new VenueNotAvailableException(VENUE_NOT_AVAILABLE.getMessage()));
        fetchedVenue.setVenueName(updatedVenue.getVenueName());
        fetchedVenue.setVenueAddress(updatedVenue.getVenueAddress());
        Venue savedVenue = saveVenue(fetchedVenue);
        return savedVenue;
    }

}
