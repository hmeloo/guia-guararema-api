package br.com.guiaguararema.application;

import br.com.guiaguararema.application.pipeline.intent.IntentRouter;
import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.port.IntentAliasPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntentRouterTest {
    @Test
    void shouldRouteEvent() {
        IntentRouter router = new IntentRouter(token -> Optional.empty());
        assertEquals(IntentType.FIND_EVENT, router.route("evento hoje"));
    }

    @Test
    void shouldUseAlias() {
        IntentAliasPort alias = token -> Optional.of(IntentType.GET_ROUTE);
        IntentRouter router = new IntentRouter(alias);
        assertEquals(IntentType.GET_ROUTE, router.route("onde"));
    }
}
