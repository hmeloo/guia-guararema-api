package br.com.guararema.api.chat;

import jakarta.validation.constraints.NotBlank;

public record ChatTextRequest(@NotBlank String message) {
}
