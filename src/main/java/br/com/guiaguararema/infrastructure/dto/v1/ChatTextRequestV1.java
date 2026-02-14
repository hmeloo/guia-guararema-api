package br.com.guiaguararema.infrastructure.dto.v1;

import jakarta.validation.constraints.NotBlank;

public record ChatTextRequestV1(@NotBlank String message, Double latitude, Double longitude, String when) {}
