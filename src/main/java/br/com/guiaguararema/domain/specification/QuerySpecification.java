package br.com.guiaguararema.domain.specification;

public interface QuerySpecification {
    boolean isSatisfiedBy(String normalizedQuery);
}
