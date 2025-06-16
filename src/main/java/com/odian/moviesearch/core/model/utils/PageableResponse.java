package com.odian.moviesearch.core.model.utils;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PageableResponse <T> {
    private final List<T> items;
    private final int currentPage;
    private final int size;
    private final int totalItems;
    private final int totalPages;

    public static <T> PageableResponse<T> of(List<T> items, int page, int size, int totalItems) {
        int totalPages = size > 0 ? (int) ((totalItems + size - 1) / size) : 0;
        return new PageableResponse<>(items, page, size, totalItems, totalPages);
    }
}
