package com.webculcate.eventservicecore.model.entity;

import com.webculcate.eventservicecore.model.entity.embedded.TimeLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.webculcate.eventservicecore.constant.ServiceConstant.VENUE_SEQUENCE_NAME;
import static com.webculcate.eventservicecore.constant.ServiceConstant.VENUE_TABLE_NAME;

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

    @Column(nullable = false)
    private String venueName;

    // TODO make it a separate entity Address
    @Column(nullable = false)
    private String venueAddress;

    @Embedded
    private TimeLog timeLog;

    @Version
    private Long version;

    public Venue(Long venueId, Long version) {
        this.setVenueId(venueId);
        this.setVersion(version);
    }
}
