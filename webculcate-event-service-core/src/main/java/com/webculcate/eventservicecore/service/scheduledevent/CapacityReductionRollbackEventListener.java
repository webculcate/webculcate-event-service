package com.webculcate.eventservicecore.service.scheduledevent;

import com.webculcate.eventservicecore.model.dto.scheduledevent.CapacityUpdateRequest;
import com.webculcate.eventservicecore.model.external.eventreservation.CapacityReductionRollbackEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.webculcate.eventservicecore.constant.CapacityUpdateType.INCREASE_CAPACITY;
import static com.webculcate.eventservicecore.constant.ServiceConstant.LISTENER_VALUE_DEFAULT_TYPE;
import static com.webculcate.eventservicecore.constant.ServiceConstant.WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK;

@Slf4j
@Service
@RequiredArgsConstructor
public class CapacityReductionRollbackEventListener {

    private final EventSchedulerServiceManager eventSchedulerServiceManager;

    @KafkaListener(topics = WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK,
            properties = {LISTENER_VALUE_DEFAULT_TYPE})
    public void listenToCapacityReductionRollbackEvent(CapacityReductionRollbackEvent capacityReductionRollbackEvent) {
        IEventSchedulerService service = eventSchedulerServiceManager.getEventSchedulerService();
        service.updateCapacity(new CapacityUpdateRequest(
                capacityReductionRollbackEvent.getScheduledEventId(),
                capacityReductionRollbackEvent.getCreditCapacity(),
                INCREASE_CAPACITY
        ));
    }

}
