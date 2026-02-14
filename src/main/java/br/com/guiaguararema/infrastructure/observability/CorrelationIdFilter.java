package br.com.guiaguararema.infrastructure.observability;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String id = request.getHeader("X-Correlation-Id");
        if (id == null || id.isBlank()) id = UUID.randomUUID().toString();
        MDC.put("correlationId", id);
        response.addHeader("X-Correlation-Id", id);
        try { filterChain.doFilter(request, response); } finally { MDC.remove("correlationId"); }
    }
}
