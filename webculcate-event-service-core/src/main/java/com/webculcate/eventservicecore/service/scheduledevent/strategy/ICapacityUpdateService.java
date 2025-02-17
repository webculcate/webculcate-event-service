package com.webculcate.eventservicecore.service.scheduledevent.strategy;

import com.webculcate.eventservicecore.model.dto.scheduledevent.CapacityUpdateRequest;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;

public interface ICapacityUpdateService {

    boolean validate(CapacityUpdateRequest request, ScheduledEvent fetchedEvent);

    void performUpdate(CapacityUpdateRequest request, ScheduledEvent fetchedEvent);

}
