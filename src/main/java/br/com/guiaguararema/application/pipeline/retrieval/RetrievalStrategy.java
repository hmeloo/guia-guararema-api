package br.com.guiaguararema.application.pipeline.retrieval;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.IntentType;

import java.util.List;

public interface RetrievalStrategy {
    IntentType supports();
    List<SearchResultItem> retrieve(ChatQuery query, String normalizedQuery);
}
