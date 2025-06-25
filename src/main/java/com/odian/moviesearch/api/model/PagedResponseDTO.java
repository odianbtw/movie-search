package com.odian.moviesearch.api.model;

import java.util.List;

public record PagedResponseDTO<T>(
    Long totalItems,
    Integer totalPages,
    Integer currentPage,
    Integer pageSize,
    List<T> items
) {
}
