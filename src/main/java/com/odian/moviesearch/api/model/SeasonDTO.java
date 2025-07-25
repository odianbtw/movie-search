package com.odian.moviesearch.api.model;

import java.util.Set;

public record SeasonDTO(
        short seasonNumber,
        Set<EpisodeDTO> episodes
) {
}
