package com.odian.moviesearch.api.model;

import java.time.LocalDate;
import java.util.UUID;

public record FilmItemDTO(
    UUID id,
    String name,
    String posterUrl,
    String description,
    LocalDate releaseDate,
    StatisticsDTO statistics
) {
}
