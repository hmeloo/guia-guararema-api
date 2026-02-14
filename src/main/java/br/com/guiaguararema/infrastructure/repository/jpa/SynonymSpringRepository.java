package br.com.guiaguararema.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SynonymSpringRepository extends JpaRepository<SynonymJpaEntity, UUID> {}
