package br.com.guiaguararema.domain.port;

import br.com.guiaguararema.domain.entity.SearchResultItem;

import java.util.List;

public interface EventSearchPort {
    List<SearchResultItem> search(String normalizedQuery, int limit);
}
