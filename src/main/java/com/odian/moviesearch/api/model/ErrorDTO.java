package com.odian.moviesearch.api.model;

import java.time.Instant;

public record ErrorDTO(
        Integer code,
        String message,
        Instant timestamp
) {
}
