package com.odian.moviesearch.core.model;

import com.odian.moviesearch.core.model.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {
    private Long id;
    private String url;
    private MediaType mediaType;
}
