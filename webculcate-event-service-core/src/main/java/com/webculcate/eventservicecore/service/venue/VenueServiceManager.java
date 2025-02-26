package com.webculcate.eventservicecore.service.venue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.webculcate.eventservicecore.constant.ServiceConstant.PROXY_ENABLED;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.DEFAULT_VENUE_SERVICE;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.VENUE_SERVICE_PROXY;

@Slf4j
@Service
@RequiredArgsConstructor
public class VenueServiceManager {

    private final Map<String, IVenueService> serviceMap;

    @Value(PROXY_ENABLED)
    private boolean proxyEnabled;

    public IVenueService getVenueService() {
        if (proxyEnabled)
            return serviceMap.get(VENUE_SERVICE_PROXY);
        else
            return serviceMap.get(DEFAULT_VENUE_SERVICE);
    }
}
