package com.webculcate.eventservicecore.model.entity;

import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.webculcate.eventservicecore.constant.ServiceConstant.*;

@Data
@Builder
@Entity
@Table(name = VENUE_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Venue {

    @Id
    @SequenceGenerator(
            name = VENUE_SEQUENCE_NAME,
            sequenceName = VENUE_SEQUENCE_NAME,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = VENUE_SEQUENCE_NAME
    )
    private Long venueId;

    private String venueName;

    // TODO make it a separate entity Address
    private String venueAddress;

    @Embedded
    private TimeLog timeLog;

    @Version
    private Long version;
}
