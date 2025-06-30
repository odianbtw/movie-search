package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.model.enums.MovieRole;
import jakarta.validation.constraints.NotNull;

public record MovieCreditRequest(
        @NotNull Long personId,
        @NotNull MovieRole role
) {
}
