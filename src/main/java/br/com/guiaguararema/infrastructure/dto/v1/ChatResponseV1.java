package br.com.guiaguararema.infrastructure.dto.v1;

import br.com.guiaguararema.application.dto.ChatResult;
import br.com.guiaguararema.domain.entity.SearchResultItem;

import java.util.List;
import java.util.Map;

public record ChatResponseV1(String answer, String matchType, String intent, List<SearchResultItem> results,
                             String disclaimer, Map<String, Object> cache, Map<String, String> audit) {
    public static ChatResponseV1 from(ChatResult result) {
        return new ChatResponseV1(
                result.answer(),
                result.matchType() != null ? result.matchType().name() : "NO_RESULT",
                result.intent() != null ? result.intent().name() : "NO_RESULT",
                result.results() != null ? result.results() : List.of(),
                result.disclaimer(),
                Map.of("hit", result.cacheHit(), "key", result.cacheKey()),
                Map.of("originalQuery", result.originalQuery() != null ? result.originalQuery() : "",
                        "normalizedQuery", result.normalizedQuery() != null ? result.normalizedQuery() : "")
        );
    }
}
