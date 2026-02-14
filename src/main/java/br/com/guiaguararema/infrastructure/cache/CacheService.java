package br.com.guiaguararema.infrastructure.cache;

import br.com.guiaguararema.application.dto.ChatResult;
import br.com.guiaguararema.domain.port.CachePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class CacheService {
    private final CachePort cachePort;
    private final MeterRegistry meterRegistry;
    private final ObjectMapper mapper = new ObjectMapper();
    @Value("${CACHE_JITTER_SECONDS:15}") private int jitter;

    public CacheService(CachePort cachePort, MeterRegistry meterRegistry) {
        this.cachePort = cachePort;
        this.meterRegistry = meterRegistry;
    }

    public String buildKey(String intent, String query, Double lat, Double lng, String when) {
        return "chat:v1:%s:%s:%s:%s:%s".formatted(intent, query, lat, lng, when);
    }

    public Optional<String> get(String key) {
        Optional<String> value = cachePort.get(key);
        meterRegistry.counter(value.isPresent() ? "cache_hit_total" : "cache_miss_total").increment();
        return value;
    }

    public void put(String key, String val, Duration ttl) {
        long jitterSeconds = (long) (Math.random() * Math.max(jitter, 1));
        cachePort.put(key, val, ttl.plusSeconds(jitterSeconds));
    }

    public String encode(ChatResult result) {
        try { return mapper.writeValueAsString(result); }
        catch (Exception e) { return "{}"; }
    }

    public ChatResult decode(String json, String key) {
        try {
            ChatResult decoded = mapper.readValue(json, ChatResult.class);
            return new ChatResult.Builder()
                    .answer(decoded.answer())
                    .matchType(decoded.matchType())
                    .intent(decoded.intent())
                    .results(decoded.results())
                    .disclaimer(decoded.disclaimer())
                    .cacheHit(true)
                    .cacheKey(key)
                    .originalQuery(decoded.originalQuery())
                    .normalizedQuery(decoded.normalizedQuery())
                    .build();
        } catch (Exception e) {
            return new ChatResult.Builder().answer("Cache inválido").cacheHit(true).cacheKey(key).build();
        }
    }
}
