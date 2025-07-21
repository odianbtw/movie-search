package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record CountryDTO(Integer id, @NotNull String name) {
}
