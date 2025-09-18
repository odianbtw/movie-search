package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record PersonCreateRequest(
        @NotNull String name,
        @NotNull String biography,
        @NotNull String photoUrl,
        @NotNull Set<KeywordDTO> keywords
) {
}
