package com.webculcate.eventservicecore.controller;

import com.webculcate.eventservicecore.model.dto.venue.VenueCreationRequest;
import com.webculcate.eventservicecore.model.dto.venue.VenueCreationResponse;
import com.webculcate.eventservicecore.service.venue.IVenueService;
import com.webculcate.eventservicecore.service.venue.VenueServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/venue/v1")
@RequiredArgsConstructor
public class VenueController {

    private final VenueServiceManager venueServiceManager;

    @PostMapping
    public ResponseEntity<VenueCreationResponse> createNewUser(@RequestBody VenueCreationRequest request) {
        IVenueService venueService = venueServiceManager.getVenueService();
        return new ResponseEntity<>(venueService.createVenue(request), OK);
    }
}
