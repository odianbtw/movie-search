package com.odian.moviesearch.api.model;

import java.util.Set;

public record PagedResponseDTO<T>(
        Long totalItems,
        Integer totalPages,
        Integer currentPage,
        Integer pageSize,
        Set<T> items
) {
}
