package com.odian.moviesearch.core.model;



import java.util.List;


public record PagedResponse<T>(long totalItems, int totalPages, int currentPage, int pageSize, List<T> items) {
}
