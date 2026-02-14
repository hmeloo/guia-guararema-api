package br.com.guararema.api.domain.repository;

import br.com.guararema.api.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
