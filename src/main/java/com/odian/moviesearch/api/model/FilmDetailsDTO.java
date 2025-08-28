package com.odian.moviesearch.api.model;

import java.time.LocalDate;
import java.util.Set;

public record FilmDetailsDTO(
        String tagline,
        String description,
        LocalDate releaseDate,
        Integer runtime,
        StatisticsDTO statistics,
        EssentialFilmMediaDTO filmMedia,
        Set<NamedPersonItemDTO> directors,
        Set<GenreDTO> genres,
        Set<CountryDTO> countries,
        Set<StudioDTO> studios,
        Set<LanguageDTO> languages,
        Set<KeywordDTO> keywords
) {
}
