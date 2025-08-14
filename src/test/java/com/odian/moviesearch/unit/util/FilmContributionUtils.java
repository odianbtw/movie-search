package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.ContributorType;
import com.odian.moviesearch.dao.postgres.entity.FilmContributionEntity;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;

import java.time.Instant;
import java.util.UUID;

public class FilmContributionUtils {
    public static FilmContributionEntity getFilmContributionEntity (FilmEntity film, PersonEntity person) {
        return new FilmContributionEntity(
                UUID.randomUUID(),
                film,
                person,
                ContributorType.DIRECTOR,
                null,
                null,
                Instant.now(),
                Instant.now()
        );
    }
}
