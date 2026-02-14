package br.com.guararema.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "embedding_chunk")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmbeddingChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_document_id")
    private SourceDocument sourceDocument;

    @Column(columnDefinition = "text")
    private String chunkText;

    @Column(columnDefinition = "vector(1536)")
    private String embedding;
}
