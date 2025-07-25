package com.odian.moviesearch.core.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pageable {
    private long pageSize = 20;
    private int currentPage = 0; // zero-based
    @Setter
    private Sortable sortable;
    @Setter
    private List<Parameter> params = new ArrayList<>();

    public void setPageSize(long pageSize) {
        if (pageSize < 1 || pageSize > 100)
            throw new IllegalArgumentException("Page size cannot be less than 1 and larger than 100");
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage < 0)
            throw new IllegalArgumentException("Page cannot be less than 0");
        this.currentPage = currentPage;
    }

}
