package com.webculcate.eventservicecore.service.scheduledevent.strategy.impl;

import com.webculcate.eventservicecore.exception.scheduledevent.InsufficientCapacityException;
import com.webculcate.eventservicecore.model.dto.scheduledevent.CapacityUpdateRequest;
import com.webculcate.eventservicecore.model.entity.ScheduledEvent;
import com.webculcate.eventservicecore.service.scheduledevent.strategy.ICapacityUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static com.webculcate.eventservicecore.constant.CapacityUpdateType.DECREASE_CAPACITY;

@Slf4j
@Service(DECREASE_CAPACITY)
@Lazy
@RequiredArgsConstructor
public class CapacityDecreaseService implements ICapacityUpdateService {

    @Override
    public boolean validate(CapacityUpdateRequest request, ScheduledEvent fetchedEvent) {
        Integer requestedCapacity = request.getCapacity();
        Integer availableCapacity = fetchedEvent.getCapacity();
        if(availableCapacity < requestedCapacity)
            throw new InsufficientCapacityException();
        else
            return true;
    }

    @Override
    public void performUpdate(CapacityUpdateRequest request, ScheduledEvent fetchedEvent) {
        Integer requestedCapacity = request.getCapacity();
        Integer availableCapacity = fetchedEvent.getCapacity();
        fetchedEvent.setCapacity(availableCapacity-requestedCapacity);
    }

}
