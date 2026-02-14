package br.com.guiaguararema.infrastructure.integration.llm;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MockLlmClient implements LlmClient {
    private final Counter llmCounter;

    public MockLlmClient(MeterRegistry meterRegistry) {
        this.llmCounter = meterRegistry.counter("llm_usage_total");
    }

    @Override
    public String complete(String prompt) {
        llmCounter.increment();
        return "resposta llm mock";
    }
}
