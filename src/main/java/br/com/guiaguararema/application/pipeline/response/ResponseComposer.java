package br.com.guiaguararema.application.pipeline.response;

import br.com.guiaguararema.application.dto.ChatResult;
import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.enums.MatchType;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseComposer {
    private final MeterRegistry meterRegistry;

    public ResponseComposer(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public ChatResult compose(String originalQuery, String normalizedQuery, IntentType intent, List<SearchResultItem> items,
                              boolean cacheHit, String cacheKey) {
        if (items.isEmpty()) {
            meterRegistry.counter("no_result_total").increment();
            return new ChatResult.Builder()
                    .answer("Não encontramos \"%s\" em Guararema.".formatted(originalQuery))
                    .matchType(MatchType.NO_RESULT)
                    .intent(IntentType.NO_RESULT)
                    .results(List.of())
                    .disclaimer("Tente outra categoria local; não expandimos para outras cidades automaticamente.")
                    .cacheHit(cacheHit)
                    .cacheKey(cacheKey)
                    .originalQuery(originalQuery)
                    .normalizedQuery(normalizedQuery)
                    .build();
        }

        MatchType matchType = items.getFirst().matchType();
        if (matchType == MatchType.CATEGORY_FALLBACK) meterRegistry.counter("fallback_total").increment();

        String answer = matchType == MatchType.CATEGORY_FALLBACK
                ? "Não encontramos exatamente \"%s\" em Guararema; aqui estão opções parecidas.".formatted(originalQuery)
                : "Encontrei opções em Guararema.";

        return new ChatResult.Builder()
                .answer(answer)
                .matchType(matchType)
                .intent(intent)
                .results(items.stream().limit(3).toList())
                .disclaimer(matchType == MatchType.CATEGORY_FALLBACK ? "Fallback por categoria aplicado." : null)
                .cacheHit(cacheHit)
                .cacheKey(cacheKey)
                .originalQuery(originalQuery)
                .normalizedQuery(normalizedQuery)
                .build();
    }
}
