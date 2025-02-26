package com.webculcate.eventservicecore.model.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.webculcate.eventservicecore.constant.ServiceConstant.END_TIME_COLUMN_NAME;
import static com.webculcate.eventservicecore.constant.ServiceConstant.START_TIME_COLUMN_NAME;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TimeRange {

    @Column(name = START_TIME_COLUMN_NAME, nullable = false)
    private Long startTime;

    @Column(name = END_TIME_COLUMN_NAME, nullable = false)
    private Long endTime;

}
