package br.com.guiaguararema.infrastructure.repository.adapter;

import br.com.guiaguararema.domain.port.SynonymPort;
import br.com.guiaguararema.infrastructure.repository.jpa.SynonymSpringRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SynonymAdapter implements SynonymPort {
    private final SynonymSpringRepository repository;
    public SynonymAdapter(SynonymSpringRepository repository) { this.repository = repository; }
    public Map<String, String> synonyms() {
        return repository.findAll().stream().collect(Collectors.toMap(s -> s.getTerm().toLowerCase(), s -> s.getSynonym().toLowerCase(), (a,b) -> a));
    }
}
