package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.model.enums.MovieRole;

public record MovieCreditDTO(
        Long id,
        PersonItemDTO person,
        MovieRole role
) {
}
