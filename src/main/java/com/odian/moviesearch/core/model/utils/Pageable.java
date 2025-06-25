package com.odian.moviesearch.core.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageable {
    private int pageSize = 20;
    private int currentPage = 0;
    private Sortable sortable = new Sortable();
    private List<Parameter> parameters;
}
