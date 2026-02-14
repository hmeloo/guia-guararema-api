package br.com.guiaguararema.domain.port;

import br.com.guiaguararema.domain.entity.SearchResultItem;

import java.util.List;

public interface ListingSearchPort {
    List<SearchResultItem> search(String normalizedQuery, Double latitude, Double longitude, String when, int limit);
}
