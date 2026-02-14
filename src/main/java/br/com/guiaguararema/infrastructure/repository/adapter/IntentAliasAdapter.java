package br.com.guiaguararema.infrastructure.repository.adapter;

import br.com.guiaguararema.domain.enums.IntentType;
import br.com.guiaguararema.domain.port.IntentAliasPort;
import br.com.guiaguararema.infrastructure.repository.jpa.IntentAliasSpringRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntentAliasAdapter implements IntentAliasPort {
    private final IntentAliasSpringRepository repository;
    public IntentAliasAdapter(IntentAliasSpringRepository repository) { this.repository = repository; }

    public Optional<IntentType> resolve(String token) {
        return repository.findByAlias(token).map(a -> IntentType.valueOf(a.getCanonicalIntent()));
    }
}
