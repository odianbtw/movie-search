package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExternalLinks {
    private String imdbUrl;
    private String tmdbUrl;
}
