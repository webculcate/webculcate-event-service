package com.webculcate.eventservicecore.service.event.factory;

import com.webculcate.eventservicecore.model.dto.event.EventCreationRequest;
import com.webculcate.eventservicecore.model.dto.venue.VenueCreationRequest;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.Venue;

public interface IEventFactory {

    Event generateEvent(EventCreationRequest request);

}
