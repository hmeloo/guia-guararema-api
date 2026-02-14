package br.com.guiaguararema.application.usecase;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.application.dto.ChatResult;
import br.com.guiaguararema.infrastructure.integration.stt.SttClient;
import org.springframework.stereotype.Service;

@Service
public class ProcessChatAudioUseCase {
    private final SttClient sttClient;
    private final ProcessChatTextUseCase textUseCase;

    public ProcessChatAudioUseCase(SttClient sttClient, ProcessChatTextUseCase textUseCase) {
        this.sttClient = sttClient;
        this.textUseCase = textUseCase;
    }

    public AudioResult execute(byte[] audio, Double latitude, Double longitude) {
        String transcription = sttClient.transcribe(audio);
        ChatResult result = textUseCase.execute(new ChatQuery(transcription, latitude, longitude, "now"));
        return new AudioResult(transcription, result);
    }

    public record AudioResult(String transcription, ChatResult result) {}
}
