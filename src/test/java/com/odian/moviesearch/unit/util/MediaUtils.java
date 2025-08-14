package com.odian.moviesearch.unit.util;

import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.core.domain.model.MediaType;
import com.odian.moviesearch.dao.postgres.entity.EssentialFilmMediaEntity;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;

import java.time.Instant;
import java.util.UUID;

public class MediaUtils {

    public static MediaEntity getMediaEntity () {
        return new MediaEntity(
                UUID.randomUUID(),
                "some.com",
                MediaType.PHOTO,
                Instant.now(),
                Instant.now()
        );
    }

    public static EssentialFilmMediaEntity getEssentialMediaEntity (FilmEntity film) {
        return new EssentialFilmMediaEntity(
                UUID.randomUUID(),
                film,
                getMediaEntity(),
                getMediaEntity(),
                getMediaEntity()
        );
    }

    public static Media getMedia () {
        return new Media(
                UUID.randomUUID(),
                "some.jpg",
                MediaType.PHOTO
        );
    }

}
