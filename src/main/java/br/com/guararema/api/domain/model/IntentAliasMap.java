package br.com.guararema.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "intent_alias_map")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntentAliasMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String alias;

    @Column(nullable = false)
    private String mappedIntent;
}
