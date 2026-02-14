package br.com.guiaguararema.infrastructure.integration.stt;

public interface SttClient {
    String transcribe(byte[] audioBytes);
}
