package com.webculcate.eventservicecore.controller.scheduledevent;

import com.webculcate.eventservicecore.model.dto.scheduledevent.*;
import com.webculcate.eventservicecore.service.scheduledevent.EventSchedulerServiceManager;
import com.webculcate.eventservicecore.service.scheduledevent.IEventSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("event/schedule/v1")
@RequiredArgsConstructor
public class ScheduledEventController {

    private final EventSchedulerServiceManager eventSchedulerServiceManager;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledEventDto> getScheduledEvent(@PathVariable("id") Long id) {
        IEventSchedulerService service = eventSchedulerServiceManager.getEventSchedulerService();
        return new ResponseEntity<>(service.getScheduledEvent(id), OK);
    }

    @GetMapping("/conflicts")
    public ResponseEntity<EventConflictResponse> getEventConflicts(@RequestBody TimeRangeDto timeRange) {
        IEventSchedulerService service = eventSchedulerServiceManager.getEventSchedulerService();
        return new ResponseEntity<>(service.getEventConflicts(timeRange), OK);
    }

    @PostMapping
    public ResponseEntity<ScheduledEventResponse> scheduleEvent(@RequestBody CreateEventScheduleRequest request) {
        IEventSchedulerService service = eventSchedulerServiceManager.getEventSchedulerService();
        return new ResponseEntity<>(service.scheduleEvent(request), OK);
    }

    @PostMapping("/capacity")
    public ResponseEntity<CapacityUpdateResponse> updateCapacity(@RequestBody CapacityUpdateRequest request) {
        IEventSchedulerService service = eventSchedulerServiceManager.getEventSchedulerService();
        return new ResponseEntity<>(service.updateCapacity(request), OK);
    }

    @PutMapping
    public ResponseEntity<ScheduledEventResponse> updateEvent(@RequestBody UpdateEventScheduleRequest request) {
        IEventSchedulerService service = eventSchedulerServiceManager.getEventSchedulerService();
        return new ResponseEntity<>(service.updateEventSchedule(request), OK);
    }

}
