package br.com.guiaguararema.infrastructure.cache;

import br.com.guiaguararema.domain.port.CachePort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class RedisCacheAdapter implements CachePort {
    private final StringRedisTemplate redis;
    public RedisCacheAdapter(StringRedisTemplate redis) { this.redis = redis; }
    public Optional<String> get(String key) { return Optional.ofNullable(redis.opsForValue().get(key)); }
    public void put(String key, String value, Duration ttl) { redis.opsForValue().set(key, value, ttl); }
}
