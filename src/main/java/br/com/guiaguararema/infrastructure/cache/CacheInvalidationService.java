package br.com.guiaguararema.infrastructure.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheInvalidationService {
    private final StringRedisTemplate redisTemplate;

    public CacheInvalidationService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void invalidateChatKeys() {
        var keys = redisTemplate.keys("chat:v1:*");
        if (keys != null && !keys.isEmpty()) redisTemplate.delete(keys);
    }
}
