package br.com.guiaguararema.infrastructure.integration.ingest;

import br.com.guiaguararema.infrastructure.cache.CacheInvalidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IngestionService {
    private static final Logger log = LoggerFactory.getLogger(IngestionService.class);
    private final CacheInvalidationService cacheInvalidationService;

    public IngestionService(CacheInvalidationService cacheInvalidationService) {
        this.cacheInvalidationService = cacheInvalidationService;
    }

    @Scheduled(cron = "${INGEST_CRON:0 0/30 * * * *}")
    public void scheduled() {
        runWithRetry("scheduler");
    }

    public void runManual() {
        runWithRetry("manual");
    }

    private void runWithRetry(String source) {
        int attempts = 0;
        while (attempts < 3) {
            attempts++;
            try {
                run(source, attempts);
                cacheInvalidationService.invalidateChatKeys();
                return;
            } catch (Exception ex) {
                log.warn("ingestion_retry source={} attempt={} error={}", source, attempts, ex.getMessage());
                sleep(250L * attempts);
            }
        }
        log.error("ingestion_failed source={} attempts=3", source);
    }

    private void run(String source, int attempt) {
        log.info("ingestion_started source={} attempt={}", source, attempt);
        log.info("ingestion_finished source={} attempt={}", source, attempt);
    }

    private void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }
}
