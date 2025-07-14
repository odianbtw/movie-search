package com.odian.moviesearch.core.domain.model;

import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Data
public class TitleInfo {
    private String slogan;
    private String description;
    private Set<Genre> genres;
    private Set<Country> countries;
    private Set<ProductionCompany> productionCompanies;
    private AgeRating ageRating;
    private Set<Media> medias;
    private Integer budget;
    private Integer revenue;


    public Optional<Media> getCover() {
        return medias.stream()
                .filter(media -> media.getMediaType() == MediaType.COVER)
                .findFirst();
    }

    public Optional<Media> getTrailer() {
        return medias.stream()
                .filter(media -> media.getMediaType() == MediaType.TRAILER)
                .findFirst();
    }
}
