package br.com.guararema.api.ingest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/ingest")
public class AdminIngestController {

    @PostMapping("/run")
    public ResponseEntity<Map<String, Object>> runIngest() {
        return ResponseEntity.accepted().body(Map.of(
                "status", "QUEUED",
                "startedAt", OffsetDateTime.now().toString(),
                "source", "MVP-manual-trigger"
        ));
    }
}
