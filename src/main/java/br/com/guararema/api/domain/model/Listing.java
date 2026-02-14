package br.com.guararema.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "listing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String segment;
    private String subcategory;

    @Column(columnDefinition = "text")
    private String tags;

    @Column(name = "description_short", columnDefinition = "text")
    private String descriptionShort;

    private String address;
    private Double lat;
    private Double lng;
    private String mapsUrl;
    private String contactWhatsapp;
    private String instagram;
    private String phone;

    @Column(columnDefinition = "jsonb")
    private String openingHoursJson;

    private String priceLevel;
    private BigDecimal avgPrice;

    private String sourceType;
    private String sourceUrl;
    private BigDecimal confidenceScore;

    private OffsetDateTime updatedAt;
    private Boolean active;
}
