package com.webculcate.eventservicecore.controller;

import com.webculcate.eventservicecore.model.dto.venue.*;
import com.webculcate.eventservicecore.service.venue.IVenueService;
import com.webculcate.eventservicecore.service.venue.VenueServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/venue/v1")
@RequiredArgsConstructor
public class VenueController {

    private final VenueServiceManager venueServiceManager;

    @GetMapping("/{id}")
    public ResponseEntity<VenueDto> getVenue(@PathVariable("id") Long id) {
        IVenueService venueService = venueServiceManager.getVenueService();
        return new ResponseEntity<>(venueService.getVenue(id), OK);
    }

    @PostMapping
    public ResponseEntity<VenueCreationResponse> createNewVenue(@RequestBody VenueCreationRequest request) {
        IVenueService venueService = venueServiceManager.getVenueService();
        return new ResponseEntity<>(venueService.createVenue(request), OK);
    }

    @PutMapping
    public ResponseEntity<VenueUpdateResponse> updateVenue(@RequestBody VenueUpdateRequest request) {
        IVenueService venueService = venueServiceManager.getVenueService();
        return new ResponseEntity<>(venueService.updateVenue(request), OK);
    }
}
