package br.com.guiaguararema.infrastructure.controller.v1;

import br.com.guiaguararema.application.usecase.RunIngestionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/ingest")
public class AdminControllerV1 {
    private final RunIngestionUseCase runIngestionUseCase;

    public AdminControllerV1(RunIngestionUseCase runIngestionUseCase) {
        this.runIngestionUseCase = runIngestionUseCase;
    }

    @PostMapping("/run")
    public ResponseEntity<Map<String, Object>> run() {
        runIngestionUseCase.execute();
        return ResponseEntity.accepted().body(Map.of("status", "QUEUED", "at", OffsetDateTime.now().toString()));
    }
}
