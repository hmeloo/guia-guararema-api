package br.com.guararema.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private OffsetDateTime startAt;
    private OffsetDateTime endAt;
    private String locationName;
    private Double lat;
    private Double lng;
    private String mapsUrl;
    private String sourceUrl;
    private OffsetDateTime updatedAt;
    private String status;
}
