package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record GenreDTO(
        @NotNull Integer id,
        String name
) {
}
