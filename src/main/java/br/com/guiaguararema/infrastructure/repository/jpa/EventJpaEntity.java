package br.com.guiaguararema.infrastructure.repository.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "event")
public class EventJpaEntity {
    @Id
    private UUID id;
    private String title;
    @Column(name = "normalized_title")
    private String normalizedTitle;
    @Column(name = "start_at")
    private OffsetDateTime startAt;
    @Column(name = "end_at")
    private OffsetDateTime endAt;
    @Column(name = "venue_name")
    private String venueName;
    private String address;
    private Double latitude;
    private Double longitude;
}
