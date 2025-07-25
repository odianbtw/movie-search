package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class Episode implements Comparable<Episode> {
    private final Id id;
    private short seasonNumber;
    private short episodeNumber;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Integer durationMinutes;
    private Float score;
    private Set<Media> medias;

    @Override
    public int compareTo(Episode o) {
        return Integer.compare(episodeNumber, o.episodeNumber);
    }
}
