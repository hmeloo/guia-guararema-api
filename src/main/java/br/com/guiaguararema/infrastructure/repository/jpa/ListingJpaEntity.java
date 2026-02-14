package br.com.guiaguararema.infrastructure.repository.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "listing")
public class ListingJpaEntity {
    @Id
    private UUID id;
    private String name;
    @Column(name = "normalized_name")
    private String normalizedName;
    private String category;
    private String address;
    private Double latitude;
    private Double longitude;
    @Column(name = "open_hours_json")
    private String openHoursJson;
    @Column(name = "is_active")
    private Boolean active;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
