package com.odian.moviesearch.api.model;

public record CompanyRequest (
        String name,
        String logoUrl,
        Integer countryId,
        String description
) {
}
