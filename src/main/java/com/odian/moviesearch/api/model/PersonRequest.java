package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonRequest(
        @NotNull String name,
        String biography,
        @NotNull Integer countryId,
        LocalDate birthDate,
        String photoUrl
) {
}
