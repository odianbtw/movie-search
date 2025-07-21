package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Media {
    private final Long id;
    private String mediaUri;
    private MediaType mediaType;
}
