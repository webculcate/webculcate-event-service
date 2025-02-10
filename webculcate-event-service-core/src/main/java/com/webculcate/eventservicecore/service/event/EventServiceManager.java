package com.webculcate.eventservicecore.service.event;

import com.webculcate.eventservicecore.service.venue.IVenueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.webculcate.eventservicecore.constant.EventServiceStrategyType.DEFAULT_EVENT_SERVICE;
import static com.webculcate.eventservicecore.constant.EventServiceStrategyType.EVENT_SERVICE_PROXY;
import static com.webculcate.eventservicecore.constant.ServiceConstant.PROXY_ENABLED;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.DEFAULT_VENUE_SERVICE;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.VENUE_SERVICE_PROXY;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceManager {

    private final Map<String, IEventService> serviceMap;

    @Value(PROXY_ENABLED)
    private boolean proxyEnabled;

    public IEventService getEventService() {
        if (proxyEnabled)
            return serviceMap.get(EVENT_SERVICE_PROXY);
        else
            return serviceMap.get(DEFAULT_EVENT_SERVICE);
    }
}
