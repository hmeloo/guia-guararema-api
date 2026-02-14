package br.com.guararema.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "source_document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sourceUrl;

    @Column(nullable = false, unique = true)
    private String contentHash;

    @Column(columnDefinition = "text")
    private String cleanedText;

    private OffsetDateTime ingestedAt;
}
