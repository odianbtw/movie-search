package com.odian.moviesearch.core.application.model;

import java.util.Set;

public record PagedResponse<T>(
        Long totalItems,
        Integer totalPages,
        Integer currentPage,
        Integer pageSize,
        Set<T> items
) {
}
