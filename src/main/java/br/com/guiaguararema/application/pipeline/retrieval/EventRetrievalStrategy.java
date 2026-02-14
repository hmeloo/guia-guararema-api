package br.com.guiaguararema.application.pipeline.retrieval;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.port.EventSearchPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventRetrievalStrategy implements RetrievalStrategy {
    private final EventSearchPort eventSearchPort;
    public EventRetrievalStrategy(EventSearchPort eventSearchPort) { this.eventSearchPort = eventSearchPort; }
    public IntentType supports() { return IntentType.FIND_EVENT; }
    public List<SearchResultItem> retrieve(ChatQuery query, String normalizedQuery) { return eventSearchPort.search(normalizedQuery, 3); }
}
