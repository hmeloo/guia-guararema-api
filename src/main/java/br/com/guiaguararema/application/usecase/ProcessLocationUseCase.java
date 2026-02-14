package br.com.guiaguararema.application.usecase;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.application.dto.ChatResult;
import org.springframework.stereotype.Service;

@Service
public class ProcessLocationUseCase {
    private final ProcessChatTextUseCase chatTextUseCase;

    public ProcessLocationUseCase(ProcessChatTextUseCase chatTextUseCase) {
        this.chatTextUseCase = chatTextUseCase;
    }

    public ChatResult execute(Double latitude, Double longitude, String query) {
        return chatTextUseCase.execute(new ChatQuery(query, latitude, longitude, "now"));
    }
}
