package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record GenreDTO(
        Integer id,
        @NotNull String name
) {
}
