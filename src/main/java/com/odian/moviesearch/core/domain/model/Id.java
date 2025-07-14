package com.odian.moviesearch.core.domain.model;

public record Id(Long id, String imdbId) {
    public Id {
        if (id <= 0) throw new IllegalArgumentException("Id must be greater than 0");
    }
}
