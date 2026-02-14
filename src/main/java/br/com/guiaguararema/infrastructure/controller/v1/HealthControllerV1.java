package br.com.guiaguararema.infrastructure.controller.v1;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HealthControllerV1 {
    private final JdbcTemplate jdbcTemplate;
    private final StringRedisTemplate redisTemplate;

    public HealthControllerV1(JdbcTemplate jdbcTemplate, StringRedisTemplate redisTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        boolean dbUp = probeDb();
        boolean redisUp = probeRedis();
        return ResponseEntity.ok(Map.of(
                "status", dbUp && redisUp ? "UP" : "DEGRADED",
                "database", dbUp ? "UP" : "DOWN",
                "redis", redisUp ? "UP" : "DOWN",
                "timestamp", OffsetDateTime.now().toString()
        ));
    }

    private boolean probeDb() {
        try { return Integer.valueOf(1).equals(jdbcTemplate.queryForObject("select 1", Integer.class)); }
        catch (Exception ignored) { return false; }
    }

    private boolean probeRedis() {
        try { return "PONG".equalsIgnoreCase(redisTemplate.getConnectionFactory().getConnection().ping()); }
        catch (Exception ignored) { return false; }
    }
}
