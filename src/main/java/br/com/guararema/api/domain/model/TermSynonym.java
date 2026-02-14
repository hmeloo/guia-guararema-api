package br.com.guararema.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "term_synonym")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermSynonym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String synonym;
}
