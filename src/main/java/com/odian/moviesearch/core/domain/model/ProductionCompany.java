package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
public class ProductionCompany {
    private final Long id;
    private String name;
    private String description;
    private Country country;
    private Set<Media> medias;


    public Optional<Media> getLogo() {
        return medias.stream()
                .filter(media -> media.getMediaType() == MediaType.LOGO)
                .findFirst();
    }
}
