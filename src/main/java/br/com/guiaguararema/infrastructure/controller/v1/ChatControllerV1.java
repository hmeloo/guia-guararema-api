package br.com.guiaguararema.infrastructure.controller.v1;

import br.com.guiaguararema.application.dto.ChatQuery;
import br.com.guiaguararema.application.usecase.ProcessChatAudioUseCase;
import br.com.guiaguararema.application.usecase.ProcessChatTextUseCase;
import br.com.guiaguararema.application.usecase.ProcessLocationUseCase;
import br.com.guiaguararema.infrastructure.dto.v1.ChatLocationRequestV1;
import br.com.guiaguararema.infrastructure.dto.v1.ChatResponseV1;
import br.com.guiaguararema.infrastructure.dto.v1.ChatTextRequestV1;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatControllerV1 {
    private final ProcessChatTextUseCase textUseCase;
    private final ProcessChatAudioUseCase audioUseCase;
    private final ProcessLocationUseCase locationUseCase;

    public ChatControllerV1(ProcessChatTextUseCase textUseCase, ProcessChatAudioUseCase audioUseCase,
                            ProcessLocationUseCase locationUseCase) {
        this.textUseCase = textUseCase;
        this.audioUseCase = audioUseCase;
        this.locationUseCase = locationUseCase;
    }

    @PostMapping("/text")
    @Operation(summary = "Chat por texto")
    public ResponseEntity<ChatResponseV1> text(@Valid @org.springframework.web.bind.annotation.RequestBody ChatTextRequestV1 request) {
        var result = textUseCase.execute(new ChatQuery(request.message(), request.latitude(), request.longitude(), request.when()));
        return ResponseEntity.ok(ChatResponseV1.from(result));
    }

    @PostMapping(value = "/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> audio(@RequestPart("file") MultipartFile file,
                                                     @RequestParam(required = false) Double latitude,
                                                     @RequestParam(required = false) Double longitude) throws IOException {
        var output = audioUseCase.execute(file.getBytes(), latitude, longitude);
        return ResponseEntity.ok(Map.of("transcription", output.transcription(), "response", ChatResponseV1.from(output.result())));
    }

    @PostMapping("/location")
    public ResponseEntity<ChatResponseV1> location(@Valid @org.springframework.web.bind.annotation.RequestBody ChatLocationRequestV1 request) {
        var result = locationUseCase.execute(request.latitude(), request.longitude(), request.query());
        return ResponseEntity.ok(ChatResponseV1.from(result));
    }
}
