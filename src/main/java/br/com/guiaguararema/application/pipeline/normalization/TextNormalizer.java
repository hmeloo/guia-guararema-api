package br.com.guiaguararema.application.pipeline.normalization;

import br.com.guiaguararema.domain.port.SynonymPort;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;

@Component
public class TextNormalizer {
    private final SynonymPort synonymPort;

    public TextNormalizer(SynonymPort synonymPort) { this.synonymPort = synonymPort; }

    public String normalize(String text) {
        String cleaned = Normalizer.normalize(text == null ? "" : text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ").trim();
        String output = cleaned;
        for (var e : synonymPort.synonyms().entrySet()) {
            output = output.replace(e.getKey(), e.getValue());
        }
        return output;
    }
}
