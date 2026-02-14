package br.com.guiaguararema.infrastructure.repository.adapter;

import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.MatchType;
import br.com.guiaguararema.domain.port.ListingSearchPort;
import br.com.guiaguararema.domain.service.OpenHoursEvaluator;
import br.com.guiaguararema.domain.service.TimeResolver;
import br.com.guiaguararema.domain.specification.CategoryHintSpecification;
import br.com.guiaguararema.infrastructure.repository.jpa.ListingJpaEntity;
import br.com.guiaguararema.infrastructure.repository.jpa.ListingSpringRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListingSearchAdapter implements ListingSearchPort {
    private final ListingSpringRepository repository;
    private final CategoryHintSpecification categoryHintSpecification = new CategoryHintSpecification();
    private final TimeResolver timeResolver = new TimeResolver();
    private final OpenHoursEvaluator openHoursEvaluator = new OpenHoursEvaluator();

    public ListingSearchAdapter(ListingSpringRepository repository) {
        this.repository = repository;
    }

    public List<SearchResultItem> search(String normalizedQuery, Double latitude, Double longitude, String when, int limit) {
        List<ListingJpaEntity> listings;
        if (latitude != null && longitude != null) {
            listings = repository.searchNearby(latitude, longitude, limit);
        } else {
            listings = repository.searchFuzzy(normalizedQuery, limit);
        }

        if (listings.isEmpty() && categoryHintSpecification.isSatisfiedBy(normalizedQuery)) {
            String category = categoryHintSpecification.category(normalizedQuery);
            listings = repository.searchByCategory(category, limit);
            return listings.stream().map(l -> map(l, MatchType.CATEGORY_FALLBACK, latitude, longitude, when)).toList();
        }

        return listings.stream().map(l -> map(l, MatchType.FUZZY, latitude, longitude, when)).toList();
    }

    private SearchResultItem map(ListingJpaEntity l, MatchType matchType, Double lat, Double lng, String when) {
        String maps = l.getLatitude() != null && l.getLongitude() != null
                ? "https://maps.google.com/?q=" + l.getLatitude() + "," + l.getLongitude() : null;
        Double distance = distanceKm(lat, lng, l.getLatitude(), l.getLongitude());
        String openStatus = openHoursEvaluator.evaluate(l.getOpenHoursJson(), timeResolver.resolve(when), when);

        return new SearchResultItem(l.getName(), l.getCategory(), l.getAddress(), distance, maps, openStatus, matchType);
    }

    private Double distanceKm(Double lat1, Double lon1, Double lat2, Double lon2) {
        if (lat1 == null || lon1 == null || lat2 == null || lon2 == null) return null;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.round((6371 * c) * 100.0) / 100.0;
    }
}
