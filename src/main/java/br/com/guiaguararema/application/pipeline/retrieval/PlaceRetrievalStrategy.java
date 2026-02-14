package br.com.guiaguararema.application.pipeline.retrieval;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.port.ListingSearchPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaceRetrievalStrategy implements RetrievalStrategy {
    private final ListingSearchPort listingSearchPort;
    public PlaceRetrievalStrategy(ListingSearchPort listingSearchPort) { this.listingSearchPort = listingSearchPort; }
    public IntentType supports() { return IntentType.FIND_PLACE; }
    public List<SearchResultItem> retrieve(ChatQuery query, String normalizedQuery) {
        return listingSearchPort.search(normalizedQuery, query.latitude(), query.longitude(), query.when(), 3);
    }
}
