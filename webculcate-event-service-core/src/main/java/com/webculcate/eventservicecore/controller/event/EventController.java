package com.webculcate.eventservicecore.controller.event;

import com.webculcate.eventservicecore.model.dto.event.EventUpdateRequest;
import com.webculcate.eventservicecore.model.dto.event.EventCreationRequest;
import com.webculcate.eventservicecore.model.dto.event.EventCreationResponse;
import com.webculcate.eventservicecore.model.dto.event.EventDto;
import com.webculcate.eventservicecore.model.dto.event.EventUpdateResponse;
import com.webculcate.eventservicecore.service.event.EventServiceManager;
import com.webculcate.eventservicecore.service.event.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/event/v1")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceManager eventServiceManager;

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable("id") Long id) {
        IEventService eventService = eventServiceManager.getEventService();
        return new ResponseEntity<>(eventService.getEvent(id), OK);
    }

    @PostMapping
    public ResponseEntity<EventCreationResponse> createNewEvent(@RequestBody EventCreationRequest request) {
        IEventService eventService = eventServiceManager.getEventService();
        return new ResponseEntity<>(eventService.createEvent(request), OK);
    }

    @PutMapping
    public ResponseEntity<EventUpdateResponse> updateEvent(@RequestBody EventUpdateRequest request) {
        IEventService eventService = eventServiceManager.getEventService();
        return new ResponseEntity<>(eventService.updateEvent(request), OK);
    }

}
