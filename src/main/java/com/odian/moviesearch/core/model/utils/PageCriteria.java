package com.odian.moviesearch.core.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageCriteria {
    private int size = 20;
    private int page = 1;
}
