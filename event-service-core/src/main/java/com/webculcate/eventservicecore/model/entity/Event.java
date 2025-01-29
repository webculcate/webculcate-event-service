package com.webculcate.eventservicecore.model.entity;

import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.webculcate.eventservicecore.constant.ServiceConstant.EVENT_SEQUENCE_NAME;
import static com.webculcate.eventservicecore.constant.ServiceConstant.EVENT_TABLE_NAME;

@Data
@Builder
@Entity
@Table(name = EVENT_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Event {

    @Id
    @SequenceGenerator(
            name = EVENT_SEQUENCE_NAME,
            sequenceName = EVENT_SEQUENCE_NAME,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = EVENT_SEQUENCE_NAME
    )
    private Long eventId;

    private String eventName;

    private String eventDescription;

    private String createdBy;

    @Embedded
    private TimeLog timeLog;

    @Version
    private Long version;
}
