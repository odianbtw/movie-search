package com.odian.moviesearch.dto;

import com.odian.moviesearch.model.enums.MovieRating;
import com.odian.moviesearch.model.enums.MovieType;

import java.time.LocalDate;
import java.util.List;

public record MovieDTO(
        Long id,
        String name,
        String slogan,
        String description,
        List<GenreDTO> genres,
        List<CountryDTO> filmedAt,
        Float score,
        String coverUrl,
        List<ProductionStudioDTO> productionStudios,
        MovieType type,
        LocalDate releaseDate,
        Integer durationTime,
        Long budget,
        Long revenue,
        MovieRating rating
) {
}
