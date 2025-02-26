package com.webculcate.eventservicecore.service.scheduledevent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.webculcate.eventservicecore.constant.EventSchedulerStrategyType.DEFAULT_EVENT_SCHEDULER_SERVICE;
import static com.webculcate.eventservicecore.constant.EventSchedulerStrategyType.EVENT_SCHEDULER_SERVICE_PROXY;
import static com.webculcate.eventservicecore.constant.ServiceConstant.PROXY_ENABLED;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventSchedulerServiceManager {

    private final Map<String, IEventSchedulerService> serviceMap;

    @Value(PROXY_ENABLED)
    private boolean proxyEnabled;

    public IEventSchedulerService getEventSchedulerService() {
        if (proxyEnabled)
            return serviceMap.get(EVENT_SCHEDULER_SERVICE_PROXY);
        else
            return serviceMap.get(DEFAULT_EVENT_SCHEDULER_SERVICE);
    }
}
