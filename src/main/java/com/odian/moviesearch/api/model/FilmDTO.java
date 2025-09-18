package com.odian.moviesearch.api.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
public record FilmDTO(
        UUID id,
        String slug,
        String name,
        String originalName,
        ExternalLinksDTO externalLinks,
        FilmDetailsDTO details
) {
}
