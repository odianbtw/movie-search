package com.odian.moviesearch.core.application.model;

import lombok.Data;

@Data
public class Sortable {
    private Order order = Order.ASC;
    private String sortBy;
}
