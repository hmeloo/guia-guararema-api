package br.com.guiaguararema.domain.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record Listing(UUID id, String name, String normalizedName, String category, List<String> tags, String description,
                      String address, Double latitude, Double longitude, String phone, String whatsapp,
                      String instagram, String website, String openHoursJson, String sourceType,
                      BigDecimal confidenceScore, boolean active, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
