package br.com.guiaguararema.application.usecase;

import br.com.guiaguararema.infrastructure.integration.ingest.IngestionService;
import org.springframework.stereotype.Service;

@Service
public class RunIngestionUseCase {
    private final IngestionService ingestionService;

    public RunIngestionUseCase(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    public void execute() {
        ingestionService.runManual();
    }
}
