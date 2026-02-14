package br.com.guiaguararema.application.pipeline.intent;

import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.port.IntentAliasPort;
import org.springframework.stereotype.Component;

@Component
public class IntentRouter {
    private final IntentAliasPort aliasPort;

    public IntentRouter(IntentAliasPort aliasPort) { this.aliasPort = aliasPort; }

    public IntentType route(String normalized) {
        if (normalized.contains("evento")) return IntentType.FIND_EVENT;
        if (normalized.contains("rota") || normalized.contains("como chegar")) return IntentType.GET_ROUTE;
        if (normalized.contains("aberto") || normalized.contains("fecha") || normalized.contains("hora")) return IntentType.CHECK_OPEN_HOURS;
        return aliasPort.resolve(normalized.split(" ")[0]).orElse(IntentType.FIND_PLACE);
    }
}
