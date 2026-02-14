package br.com.guiaguararema.application.dto;

import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.enums.MatchType;

import java.util.List;

public record ChatResult(String answer, MatchType matchType, IntentType intent, List<SearchResultItem> results,
                         String disclaimer, boolean cacheHit, String cacheKey, String originalQuery, String normalizedQuery) {
    public static class Builder {
        private String answer;
        private MatchType matchType;
        private IntentType intent;
        private List<SearchResultItem> results;
        private String disclaimer;
        private boolean cacheHit;
        private String cacheKey;
        private String originalQuery;
        private String normalizedQuery;

        public Builder answer(String value) { this.answer = value; return this; }
        public Builder matchType(MatchType value) { this.matchType = value; return this; }
        public Builder intent(IntentType value) { this.intent = value; return this; }
        public Builder results(List<SearchResultItem> value) { this.results = value; return this; }
        public Builder disclaimer(String value) { this.disclaimer = value; return this; }
        public Builder cacheHit(boolean value) { this.cacheHit = value; return this; }
        public Builder cacheKey(String value) { this.cacheKey = value; return this; }
        public Builder originalQuery(String value) { this.originalQuery = value; return this; }
        public Builder normalizedQuery(String value) { this.normalizedQuery = value; return this; }
        public ChatResult build() {
            return new ChatResult(answer, matchType, intent, results, disclaimer, cacheHit, cacheKey, originalQuery, normalizedQuery);
        }
    }
}
