package br.com.guiaguararema.application;

import br.com.guiaguararema.domain.service.TimeResolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeResolverTest {
    @Test
    void shouldResolveTomorrow() {
        var resolver = new TimeResolver();
        var now = resolver.resolve("now");
        var tomorrow = resolver.resolve("amanha");
        assertTrue(tomorrow.isAfter(now));
    }
}
