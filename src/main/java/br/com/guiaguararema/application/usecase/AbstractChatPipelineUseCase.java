package br.com.guiaguararema.application.usecase;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.application.dto.ChatResult;
import br.com.guiaguararema.application.pipeline.intent.IntentRouter;
import br.com.guiaguararema.application.pipeline.normalization.TextNormalizer;
import br.com.guiaguararema.application.pipeline.response.ResponseComposer;
import br.com.guiaguararema.application.pipeline.retrieval.RetrievalStrategyFactory;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.infrastructure.cache.CacheService;

import java.time.Duration;

public abstract class AbstractChatPipelineUseCase {
    protected final TextNormalizer normalizer;
    protected final IntentRouter intentRouter;
    protected final RetrievalStrategyFactory retrievalFactory;
    protected final ResponseComposer composer;
    protected final CacheService cacheService;

    protected AbstractChatPipelineUseCase(TextNormalizer normalizer, IntentRouter intentRouter,
                                          RetrievalStrategyFactory retrievalFactory, ResponseComposer composer,
                                          CacheService cacheService) {
        this.normalizer = normalizer;
        this.intentRouter = intentRouter;
        this.retrievalFactory = retrievalFactory;
        this.composer = composer;
        this.cacheService = cacheService;
    }

    public ChatResult execute(ChatQuery query) {
        String normalized = normalizer.normalize(query.message());
        IntentType intent = resolveIntent(query, normalized);
        String key = cacheService.buildKey(intent.name(), normalized, query.latitude(), query.longitude(), query.when());
        var cached = cacheService.get(key);
        if (cached.isPresent()) return cacheService.decode(cached.get(), key);

        var items = retrievalFactory.get(intent).retrieve(query, normalized);
        ChatResult result = composer.compose(query.message(), normalized, intent, items, false, key);
        cacheService.put(key, cacheService.encode(result), cacheTtl());
        return result;
    }

    protected IntentType resolveIntent(ChatQuery query, String normalized) {
        return intentRouter.route(normalized);
    }

    protected Duration cacheTtl() {
        return Duration.ofSeconds(120);
    }
}
