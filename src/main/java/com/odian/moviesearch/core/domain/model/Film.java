package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
public class Film {
    private final UUID id;
    private String slug;
    private String name;
    private String originalName;
    private ExternalLinks externalLinks;
    private FilmDetails details;
}
