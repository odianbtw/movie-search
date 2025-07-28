package com.odian.moviesearch.api.model;

import com.odian.moviesearch.core.domain.model.TitleRole;

public record TitleCreditRequest(
        Long personId,
        Long titleId,
        TitleRole titleRole
) {
}
