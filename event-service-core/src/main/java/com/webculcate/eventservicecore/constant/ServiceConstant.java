package com.webculcate.eventservicecore.constant;

public class ServiceConstant {

    // database

    public static final String EVENT_TABLE_NAME = "service_event";

    public static final String EVENT_SEQUENCE_NAME = EVENT_TABLE_NAME + "_sequence";

    public static final String EVENT_FOREIGN_KEY = "fk_service_event";

    public static final String SCHEDULED_EVENT_TABLE_NAME = "service_scheduled_event";

    public static final String SCHEDULED_EVENT_SEQUENCE_NAME = SCHEDULED_EVENT_TABLE_NAME + "_sequence";

    public static final String VENUE_TABLE_NAME = "service_venue";

    public static final String VENUE_SEQUENCE_NAME = VENUE_TABLE_NAME + "_sequence";

    public static final String VENUE_FOREIGN_KEY = "fk_service_venue";

    // common

    public static final String PROXY_ENABLED = "${application.proxy.enabled}";

    public static final String STRING_SPACE = " ";

    // external
    public static final String USER_SERVICE_NAME = "USER_SERVICE_CORE";
}
