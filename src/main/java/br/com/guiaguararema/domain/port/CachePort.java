package br.com.guiaguararema.domain.port;

import java.time.Duration;
import java.util.Optional;

public interface CachePort {
    Optional<String> get(String key);
    void put(String key, String value, Duration ttl);
}
