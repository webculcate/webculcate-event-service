package com.webculcate.eventservicecore.service.event;

import com.webculcate.eventservicecore.model.dto.event.*;

public interface IEventService {

    EventCreationResponse createEvent(EventCreationRequest request);

    EventDto getEvent(Long id);

    EventUpdateResponse updateEvent(EventUpdateRequest request);

}
