package com.odian.moviesearch.api.model;

import java.time.Instant;

public record ErrorDTO(
        Integer status,
        String message,
        Instant timestamp
) {
}
