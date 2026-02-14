package br.com.guiaguararema.domain.port;

import br.com.guiaguararema.domain.enums.IntentType;

import java.util.Optional;

public interface IntentAliasPort {
    Optional<IntentType> resolve(String token);
}
