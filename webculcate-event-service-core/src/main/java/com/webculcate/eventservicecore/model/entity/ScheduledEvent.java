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
@Table(
        name = SCHEDULED_EVENT_TABLE_NAME,
        indexes = {
                @Index(columnList = START_TIME_COLUMN_NAME + INDEX_ASCENDING_SUFFIX, name = START_TIME_INDEX),
                @Index(columnList = END_TIME_COLUMN_NAME + INDEX_ASCENDING_SUFFIX, name = END_TIME_INDEX)
        }
)
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

    @Column(nullable = false)
    private Set<Long> organisedBy;

    @Column(nullable = false)
    private ScheduledEventStatus status;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Embedded
    private TimeLog timeLog;

    @Version
    private Long version;

}
