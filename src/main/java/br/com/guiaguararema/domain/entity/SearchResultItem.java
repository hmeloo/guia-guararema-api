package br.com.guiaguararema.domain.entity;

import br.com.guiaguararema.domain.enums.MatchType;

public record SearchResultItem(String name, String category, String address, Double distanceKm, String mapsUrl,
                               String openStatus, MatchType matchType) {
}
