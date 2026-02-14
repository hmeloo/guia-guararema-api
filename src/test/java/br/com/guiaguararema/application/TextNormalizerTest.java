package br.com.guiaguararema.application;

import br.com.guiaguararema.application.pipeline.normalization.TextNormalizer;
import br.com.guiaguararema.domain.port.SynonymPort;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextNormalizerTest {
    @Test
    void shouldNormalizeTypo() {
        SynonymPort synonymPort = () -> Map.of("pet shoop", "pet shop");
        TextNormalizer n = new TextNormalizer(synonymPort);
        assertEquals("tem pet shop", n.normalize("Tem Pet Shoop!!!"));
    }
}
