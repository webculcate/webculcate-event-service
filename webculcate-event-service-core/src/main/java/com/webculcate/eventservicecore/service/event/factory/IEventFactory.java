package com.webculcate.eventservicecore.service.event.factory;

import com.webculcate.eventservicecore.model.dto.event.EventCreationRequest;
import com.webculcate.eventservicecore.model.entity.Event;

public interface IEventFactory {

    Event generateEvent(EventCreationRequest request);

}
