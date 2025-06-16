package com.odian.moviesearch.core.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sortable {
    private Order order;
    private String sortBy;
}
