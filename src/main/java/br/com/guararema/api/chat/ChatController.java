package br.com.guararema.api.chat;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @PostMapping("/text")
    public ResponseEntity<Map<String, Object>> chatText(@Valid @RequestBody ChatTextRequest request) {
        return ResponseEntity.ok(Map.of(
                "message", "Fluxo MVP recebido",
                "input", request.message(),
                "pipeline", "normalizacao->intent->sql/fts->fallback"
        ));
    }

    @PostMapping(value = "/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> chatAudio(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(Map.of(
                "message", "Áudio recebido para transcrição futura",
                "fileName", file.getOriginalFilename(),
                "size", file.getSize()
        ));
    }

    @PostMapping("/location")
    public ResponseEntity<Map<String, Object>> chatLocation(@Valid @RequestBody ChatLocationRequest request) {
        return ResponseEntity.ok(Map.of(
                "message", "Localização recebida",
                "lat", request.lat(),
                "lng", request.lng(),
                "geoBucket", request.geoBucket()
        ));
    }
}
