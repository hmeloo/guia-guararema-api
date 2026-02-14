package br.com.guiaguararema.application;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.application.pipeline.intent.IntentRouter;
import br.com.guiaguararema.application.pipeline.normalization.TextNormalizer;
import br.com.guiaguararema.application.pipeline.response.ResponseComposer;
import br.com.guiaguararema.application.pipeline.retrieval.RetrievalStrategy;
import br.com.guiaguararema.application.pipeline.retrieval.RetrievalStrategyFactory;
import br.com.guiaguararema.application.usecase.ProcessChatTextUseCase;
import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.enums.MatchType;
import br.com.guiaguararema.infrastructure.cache.CacheService;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

class ProcessChatTextUseCaseTest {
    @Test
    void shouldReturnCacheHitOnSecondCall() {
        var cache = new CacheService(new InMemoryCachePort(), new SimpleMeterRegistry());
        var normalizer = new TextNormalizer(() -> Map.of());
        var router = new IntentRouter(token -> Optional.of(IntentType.FIND_PLACE));
        RetrievalStrategy strategy = Mockito.mock(RetrievalStrategy.class);
        Mockito.when(strategy.retrieve(any(), any())).thenReturn(List.of(
                new SearchResultItem("Pet Shop Guará", "pet_shop", "Rua 2", 0.5, null, "OPEN_NOW", MatchType.FUZZY)
        ));
        RetrievalStrategyFactory factory = Mockito.mock(RetrievalStrategyFactory.class);
        Mockito.when(factory.get(IntentType.FIND_PLACE)).thenReturn(strategy);

        var uc = new ProcessChatTextUseCase(normalizer, router, factory, new ResponseComposer(new SimpleMeterRegistry()), cache);
        uc.execute(new ChatQuery("pet shop", null, null, "now"));
        var second = uc.execute(new ChatQuery("pet shop", null, null, "now"));
        assertTrue(second.cacheHit());
    }

    static class InMemoryCachePort implements br.com.guiaguararema.domain.port.CachePort {
        private final java.util.Map<String, String> map = new java.util.HashMap<>();
        public Optional<String> get(String key) { return Optional.ofNullable(map.get(key)); }
        public void put(String key, String value, Duration ttl) { map.put(key, value); }
    }
}
