package com.webculcate.eventservicecore.service.event.factory.impl;

import com.webculcate.eventservicecore.model.dto.event.EventCreationRequest;
import com.webculcate.eventservicecore.model.entity.Event;
import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import com.webculcate.eventservicecore.service.event.factory.IEventFactory;

public class DefaultEventFactory implements IEventFactory {

    @Override
    public Event generateEvent(EventCreationRequest request) {
        return Event.builder()
                .eventName(request.getEventName())
                .eventDescription(request.getEventDescription())
                .createdBy(request.getCreatedBy())
                .timeLog(new TimeLog())
                .build();
    }

}
