package br.com.guiaguararema.domain.specification;

import java.util.Map;

public class CategoryHintSpecification implements QuerySpecification {
    private static final Map<String, String> HINTS = Map.of(
            "pet", "pet_shop", "farmacia", "farmacia", "pizza", "pizzaria", "hotel", "hotel", "evento", "evento"
    );

    @Override
    public boolean isSatisfiedBy(String normalizedQuery) {
        return HINTS.keySet().stream().anyMatch(normalizedQuery::contains);
    }

    public String category(String normalizedQuery) {
        return HINTS.entrySet().stream().filter(e -> normalizedQuery.contains(e.getKey())).map(Map.Entry::getValue).findFirst().orElse("comercio");
    }
}
