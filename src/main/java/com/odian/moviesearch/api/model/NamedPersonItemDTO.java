package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NamedPersonItemDTO(
        UUID id,
        @NotNull String name
) {
}
