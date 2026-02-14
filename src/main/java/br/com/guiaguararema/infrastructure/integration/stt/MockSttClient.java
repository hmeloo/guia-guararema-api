package br.com.guiaguararema.infrastructure.integration.stt;

import org.springframework.stereotype.Component;

@Component
public class MockSttClient implements SttClient {
    @Override
    public String transcribe(byte[] audioBytes) {
        return audioBytes.length > 0 ? "audio transcrito" : "audio vazio";
    }
}
