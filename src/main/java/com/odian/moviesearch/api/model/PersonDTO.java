package com.odian.moviesearch.api.model;

import java.util.Set;
import java.util.UUID;

public record PersonDTO(
        UUID id,
        String slug,
        String name,
        String biography,
        String photoUrl,
        Set<KeywordDTO> keywords
) {
}
