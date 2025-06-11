package com.odian.moviesearch.core.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Genre {
    private Integer id;
    private String name;
}
