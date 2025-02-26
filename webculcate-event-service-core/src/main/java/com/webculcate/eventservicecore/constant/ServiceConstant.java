package com.webculcate.eventservicecore.constant;

public class ServiceConstant {

    // database

    public static final String EVENT_TABLE_NAME = "service_event";

    public static final String EVENT_SEQUENCE_NAME = EVENT_TABLE_NAME + "_sequence";

    public static final String EVENT_FOREIGN_KEY = "fk_service_event";

    public static final String SCHEDULED_EVENT_TABLE_NAME = "service_scheduled_event";

    public static final String SCHEDULED_EVENT_SEQUENCE_NAME = SCHEDULED_EVENT_TABLE_NAME + "_sequence";

    public static final String START_TIME_COLUMN_NAME = "start_time";

    public static final String END_TIME_COLUMN_NAME = "end_time";

    public static final String VENUE_TABLE_NAME = "service_venue";

    public static final String VENUE_SEQUENCE_NAME = VENUE_TABLE_NAME + "_sequence";

    public static final String VENUE_FOREIGN_KEY = "fk_service_venue";

    public static final String INDEX_SUFFIX = "_index";

    public static final String INDEX_ASCENDING_SUFFIX = " ASC";

    public static final String START_TIME_INDEX = START_TIME_COLUMN_NAME + INDEX_SUFFIX;

    public static final String END_TIME_INDEX = END_TIME_COLUMN_NAME + INDEX_SUFFIX;


    // common

    public static final String PROXY_ENABLED = "${application.proxy.enabled}";

    public static final String WEBCULCATE_GROUP_ID = "${spring.kafka.consumer.group-id}";

    public static final String STRING_SPACE = " ";
    public static final String LISTENER_VALUE_DEFAULT_TYPE = "spring.json.value.default.type=com.webculcate.eventservicecore.model.external.eventreservation.CapacityReductionRollbackEvent";

    public static final Long ZERO_LONG = 0L;

    public static final Integer ZERO_INTEGER = 0;



    // external
    public static final String WEBCULCATE_USER_SERVICE = "WEBCULCATE-USER-SERVICE-CORE";

    // topic
    public static final String WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK = "webculcateCapacityReductionRollback";
}
