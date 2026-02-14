package br.com.guiaguararema.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventSpringRepository extends JpaRepository<EventJpaEntity, UUID> {
    @Query(value = """
            select * from event e
            where e.is_active = true and (similarity(e.normalized_title, :query) > 0.2 or e.title ilike concat('%', :query, '%'))
            order by e.start_at asc limit :limit
            """, nativeQuery = true)
    List<EventJpaEntity> searchFuzzy(String query, int limit);
}
