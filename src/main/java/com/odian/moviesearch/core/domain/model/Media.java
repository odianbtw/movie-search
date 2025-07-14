package com.odian.moviesearch.core.domain.model;

import lombok.Data;

@Data
public class Media {
    private final Long id;
    private String mediaUri;
    private MediaType mediaType;
}
