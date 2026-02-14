package br.com.guiaguararema.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {
    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        Window window = windows.computeIfAbsent(ip, k -> new Window());
        if (!window.allow()) { response.setStatus(429); return; }
        filterChain.doFilter(request, response);
    }

    static class Window {
        int count = 0; long start = Instant.now().getEpochSecond();
        synchronized boolean allow() {
            long now = Instant.now().getEpochSecond();
            if (now - start >= 60) { start = now; count = 0; }
            count++;
            return count <= 120;
        }
    }
}
