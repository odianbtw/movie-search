package com.odian.moviesearch.core.application.model;

public record Pageable(Integer pageSize, Integer page) {
    public Pageable {
        if (pageSize < 1 || pageSize > 20)
            throw new IllegalArgumentException("Page size value must be greater than 0 and less than 20");
        if (page < 0)
            throw new IllegalArgumentException("Page value must be positive number");
    }
}
