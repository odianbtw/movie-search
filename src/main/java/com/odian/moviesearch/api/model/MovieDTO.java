package com.odian.moviesearch.api.model;


import com.odian.moviesearch.core.model.enums.MovieRating;
import com.odian.moviesearch.core.model.enums.MovieType;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieDTO(
        Long id,
        String name,
        String slogan,
        String description,
        List<GenreDTO> genres,
        List<CountryDTO> countries,
        List<CompanyItemDTO> companies,
        Float score,
        String coverUrl,
        String trailerUrl,
        LocalDate releaseDate,
        Integer durationTime,
        MovieType movieType,
        MovieRating movieRating,
        Integer budget,
        Integer revenue
) {
}
