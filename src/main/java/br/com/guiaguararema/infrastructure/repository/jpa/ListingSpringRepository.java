package br.com.guiaguararema.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ListingSpringRepository extends JpaRepository<ListingJpaEntity, UUID> {
    @Query(value = """
            select * from listing l
            where l.is_active = true
              and (similarity(l.normalized_name, :query) > 0.25
                   or to_tsvector('portuguese', coalesce(l.name,'') || ' ' || coalesce(l.description,'')) @@ plainto_tsquery('portuguese', :query))
            order by similarity(l.normalized_name, :query) desc
            limit :limit
            """, nativeQuery = true)
    List<ListingJpaEntity> searchFuzzy(String query, int limit);

    @Query(value = """
            select * from listing l
            where l.is_active = true and l.category = :category
            limit :limit
            """, nativeQuery = true)
    List<ListingJpaEntity> searchByCategory(String category, int limit);

    @Query(value = """
            select * from listing l
            where l.is_active = true
            order by (
              6371 * acos(
                  cos(radians(:lat)) * cos(radians(l.latitude)) * cos(radians(l.longitude) - radians(:lng))
                  + sin(radians(:lat)) * sin(radians(l.latitude))
              )
            ) asc
            limit :limit
            """, nativeQuery = true)
    List<ListingJpaEntity> searchNearby(double lat, double lng, int limit);
}
