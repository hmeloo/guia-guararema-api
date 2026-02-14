package br.com.guiaguararema.application.usecase;

import br.com.guiaguararema.application.pipeline.intent.IntentRouter;
import br.com.guiaguararema.application.pipeline.normalization.TextNormalizer;
import br.com.guiaguararema.application.pipeline.response.ResponseComposer;
import br.com.guiaguararema.application.pipeline.retrieval.RetrievalStrategyFactory;
import br.com.guiaguararema.infrastructure.cache.CacheService;
import org.springframework.stereotype.Service;

@Service
public class ProcessChatTextUseCase extends AbstractChatPipelineUseCase {
    public ProcessChatTextUseCase(TextNormalizer normalizer, IntentRouter intentRouter, RetrievalStrategyFactory retrievalFactory,
                                  ResponseComposer composer, CacheService cacheService) {
        super(normalizer, intentRouter, retrievalFactory, composer, cacheService);
    }
}
