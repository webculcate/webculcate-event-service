package com.webculcate.eventservicecore.model.entity;

import com.webculcate.eventservicecore.constant.ScheduledEventStatus;
import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import com.webculcate.eventservicecore.model.entity.embedded.TimeRange;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

import static com.webculcate.eventservicecore.constant.ServiceConstant.*;

@Data
@Builder
@Entity
@Table(name = SCHEDULED_EVENT_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ScheduledEvent {

    @Id
    @SequenceGenerator(
            name = SCHEDULED_EVENT_SEQUENCE_NAME,
            sequenceName = SCHEDULED_EVENT_SEQUENCE_NAME,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SCHEDULED_EVENT_SEQUENCE_NAME
    )
    private Long scheduledEventId;

    @ManyToOne
    @JoinColumn(
            name = EVENT_FOREIGN_KEY,
            referencedColumnName = "eventId"
    )
    private Event event;

    @ManyToOne
    @JoinColumn(
            name = VENUE_FOREIGN_KEY,
            referencedColumnName = "venueId"
    )
    private Venue venue;

    @Embedded
    private TimeRange timeRange;

    private Set<Long> organisedBy;

    private ScheduledEventStatus status;

    private Integer capacity;

    private Integer maxCapacity;

    @Embedded
    private TimeLog timeLog;

    @Version
    private Long version;

}
