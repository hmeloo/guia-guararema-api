package br.com.guiaguararema.infrastructure.integration.llm;

public interface LlmClient {
    String complete(String prompt);
}
