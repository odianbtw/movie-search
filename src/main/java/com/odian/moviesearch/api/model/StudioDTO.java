package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StudioDTO(
        @NotNull UUID id,
        String slug,
        String name
) {
}
