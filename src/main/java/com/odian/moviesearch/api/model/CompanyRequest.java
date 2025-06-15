package com.odian.moviesearch.api.model;

import jakarta.validation.constraints.NotNull;

public record CompanyRequest (
        @NotNull String name,
        @NotNull String logoUrl,
        @NotNull Integer countryId,
        @NotNull String description
) {
}
