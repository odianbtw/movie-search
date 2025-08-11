package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductionStudio {
    private UUID id;
    private String slug;
    private String name;
}
