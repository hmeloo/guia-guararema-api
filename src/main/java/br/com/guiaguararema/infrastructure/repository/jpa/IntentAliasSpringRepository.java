package br.com.guiaguararema.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IntentAliasSpringRepository extends JpaRepository<IntentAliasJpaEntity, UUID> {
    Optional<IntentAliasJpaEntity> findByAlias(String alias);
}
