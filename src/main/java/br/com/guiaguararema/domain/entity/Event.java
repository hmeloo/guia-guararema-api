package br.com.guiaguararema.domain.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record Event(UUID id, String title, String normalizedTitle, String description, OffsetDateTime startAt,
                    OffsetDateTime endAt, String venueName, String address, Double latitude, Double longitude,
                    String sourceUrl, String sourceType, BigDecimal confidenceScore, boolean active,
                    OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
