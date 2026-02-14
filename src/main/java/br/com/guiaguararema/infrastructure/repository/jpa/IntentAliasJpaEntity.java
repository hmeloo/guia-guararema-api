package br.com.guiaguararema.infrastructure.repository.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "intent_alias_map")
public class IntentAliasJpaEntity {
    @Id
    private UUID id;
    private String alias;
    @Column(name = "canonical_intent")
    private String canonicalIntent;
}
