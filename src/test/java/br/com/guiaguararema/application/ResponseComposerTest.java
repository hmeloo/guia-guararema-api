package br.com.guiaguararema.application;

import br.com.guiaguararema.application.pipeline.response.ResponseComposer;
import br.com.guiaguararema.domain.entity.SearchResultItem;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.enums.MatchType;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseComposerTest {
    @Test
    void shouldCreateFallbackAnswer() {
        var composer = new ResponseComposer(new SimpleMeterRegistry());
        var result = composer.compose("mcdonalds", "mcdonalds", IntentType.FIND_PLACE,
                List.of(new SearchResultItem("Lanchonete Praça", "lanchonete", "Rua", null, null, null, MatchType.CATEGORY_FALLBACK)),
                false, "k");
        assertEquals(MatchType.CATEGORY_FALLBACK, result.matchType());
    }
}
