package com.odian.moviesearch.core.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageable {
    private int pageSize;
    private int currentPage;
    private Sortable sortable;
    private List<Parameter> parameters;
}
