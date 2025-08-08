package com.odian.moviesearch.api.model;

import java.util.UUID;

public record NamedPersonItemDTO(
        UUID id,
        String name
) {
}
