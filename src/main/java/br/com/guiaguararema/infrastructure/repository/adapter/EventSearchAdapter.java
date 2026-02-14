package br.com.guiaguararema.infrastructure.repository.adapter;

import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.MatchType;
import br.com.guiaguararema.domain.port.EventSearchPort;
import br.com.guiaguararema.infrastructure.repository.jpa.EventSpringRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventSearchAdapter implements EventSearchPort {
    private final EventSpringRepository repository;
    public EventSearchAdapter(EventSpringRepository repository) { this.repository = repository; }
    public List<SearchResultItem> search(String normalizedQuery, int limit) {
        return repository.searchFuzzy(normalizedQuery, limit).stream()
                .map(e -> new SearchResultItem(e.getTitle(), "EVENT", e.getAddress(), null, null, null, MatchType.EXACT)).toList();
    }
}
