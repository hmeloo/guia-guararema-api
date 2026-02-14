package br.com.guiaguararema.application.pipeline.retrieval;

import br.com.guiaguararema.domain.enums.IntentType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrievalStrategyFactory {
    private final List<RetrievalStrategy> strategies;
    public RetrievalStrategyFactory(List<RetrievalStrategy> strategies) { this.strategies = strategies; }

    public RetrievalStrategy get(IntentType intentType) {
        return strategies.stream().filter(s -> s.supports() == intentType).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sem strategy para intent " + intentType));
    }
}
