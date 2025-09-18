package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record LanguageDTO(
        @NotNull Integer id,
        String name
) {
}
