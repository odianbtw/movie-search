package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record CountryDTO(
        @NotNull Integer id,
        String name
) {
}
