package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
public class FilmDetails {
    private String tagline;
    private String description;
    private LocalDate releaseDate;
    private Integer runtime;
    private Statistics statistics;
    private Set<Media> medias;
    private Set<Person> directors;
    private Set<Genre> genres;
    private Set<Country> countries;
    private Set<Language> languages;
    private Set<ProductionStudio> studios;
    private Set<Keyword> keywords;

    public Optional<Media> getPoster () {
        return medias.stream()
                .filter(t -> Objects.equals(t.getMediaType(), MediaType.POSTER))
                .findFirst();
    }

    public Optional<Media> getBackdropImage () {
        return medias.stream()
                .filter(t -> Objects.equals(t.getMediaType(), MediaType.BACKDROP))
                .findFirst();
    }

    public Optional<Media> getTrailer () {
        return medias.stream()
                .filter(t -> Objects.equals(t.getMediaType(), MediaType.TRAILER))
                .findFirst();
    }


}
