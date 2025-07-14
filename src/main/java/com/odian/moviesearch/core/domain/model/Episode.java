package com.odian.moviesearch.core.domain.model;

import java.time.LocalDate;
import java.util.Set;

public class Episode {
    private Integer episodeNumber;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Integer durationMinutes;
    private Float score;
    private Set<Media> medias;
}
