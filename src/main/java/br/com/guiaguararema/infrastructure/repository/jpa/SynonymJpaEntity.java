package br.com.guiaguararema.infrastructure.repository.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "term_synonym")
public class SynonymJpaEntity {
    @Id
    private UUID id;
    private String term;
    private String synonym;
}
