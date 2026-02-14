package br.com.guararema.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlaceRequest(
        @NotBlank String name,
        @NotBlank String category,
        @Size(max = 1000) String description
) {
}
