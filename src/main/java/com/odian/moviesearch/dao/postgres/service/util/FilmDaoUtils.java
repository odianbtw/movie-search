package com.odian.moviesearch.dao.postgres.service.util;

import com.odian.moviesearch.core.domain.model.ContributorType;
import com.odian.moviesearch.core.domain.model.MediaType;
import com.odian.moviesearch.dao.postgres.entity.EssentialFilmMediaEntity;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import jakarta.persistence.EntityManager;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class FilmDaoUtils {


    public static void update(FilmEntity persistent, FilmEntity detached, EntityManager entityManager) {
        setBasicInfo(persistent, detached);
        setMedias(persistent, detached, entityManager);
        setBasicCollections(persistent, detached);
        setContributions(persistent, detached);

    }

    private static void setContributions (FilmEntity persistent, FilmEntity detached) {
        if (detached.getFilmContributions() != null) {
            persistent.getFilmContributions()
                    .removeIf(t -> Objects.equals(t.getContributorType(), ContributorType.DIRECTOR));
            detached.getFilmContributions().forEach(
                    t -> {
                        t.setId(UUID.randomUUID());
                        t.setFilm(persistent);
                    }
            );
            persistent.getFilmContributions().addAll(detached.getFilmContributions());
            persistent.setDirectors(persistent
                    .getFilmContributions()
                    .stream()
                    .filter(t -> Objects.equals(t.getContributorType(), ContributorType.DIRECTOR))
                    .collect(Collectors.toSet())
            );
        }
    }

    private static void setBasicCollections (FilmEntity persistent, FilmEntity detached) {
        if (detached.getGenres() != null) {
            persistent.getGenres().clear();
            persistent.getGenres().addAll(detached.getGenres());
        }
        if (detached.getCountries() != null) {
            persistent.getCountries().clear();
            persistent.getCountries().addAll(detached.getCountries());
        }
        if (detached.getStudios() != null) {
            persistent.getStudios().clear();
            persistent.getStudios().addAll(detached.getStudios());
        }
        if (detached.getLanguages() != null) {
            persistent.getLanguages().clear();
            persistent.getLanguages().addAll(detached.getLanguages());
        }
        if (detached.getKeywords() != null) {
            persistent.getKeywords().clear();
            persistent.getKeywords().addAll(detached.getKeywords());
        }
    }

    private static void setMedias(FilmEntity persistent, FilmEntity detached, EntityManager manager) {
        if (detached.getMedias() != null) {
            persistent.getMedias().removeIf(m -> {
                var type = m.getMediaType();
                return type == MediaType.POSTER
                        || type == MediaType.TRAILER
                        || type == MediaType.BACKDROP;
            });

            var attachedMedias = detached.getMedias().stream()
                    .map(m -> manager.getReference(MediaEntity.class, m.getId()))
                    .collect(Collectors.toSet());

            persistent.getMedias().addAll(attachedMedias);

            var poster = attachedMedias.stream()
                    .filter(t -> t.getMediaType() == MediaType.POSTER)
                    .findFirst().orElse(null);

            var backdrop = attachedMedias.stream()
                    .filter(t -> t.getMediaType() == MediaType.BACKDROP)
                    .findFirst().orElse(null);

            var trailer = attachedMedias.stream()
                    .filter(t -> t.getMediaType() == MediaType.TRAILER)
                    .findFirst().orElse(null);

            EssentialFilmMediaEntity em = persistent.getEssentialMedia();
            if (em == null) {
                em = new EssentialFilmMediaEntity();
                em.setFilm(persistent);
                persistent.setEssentialMedia(em);
            }
            em.setPoster(poster);
            em.setBackdrop(backdrop);
            em.setTrailer(trailer);
        }
    }

    private static void setBasicInfo (FilmEntity persistent, FilmEntity detached) {
        persistent.setSlug(detached.getSlug());
        persistent.setName(detached.getName());
        persistent.setOriginalName(detached.getOriginalName());
        persistent.setImdbUrl(detached.getImdbUrl());
        persistent.setTmdbUrl(detached.getTmdbUrl());
        persistent.setTagline(detached.getTagline());
        persistent.setDescription(detached.getDescription());
        persistent.setReleaseDate(detached.getReleaseDate());
        persistent.setRuntime(detached.getRuntime());
    }

    public static String refactorSortBy (String sortBy) {
        switch (sortBy) {
            case "rating" -> {
                return "ratings.rating";
            }
            case "popularity" -> {
                return "popularity.rating";
            }
            case "trending" -> {
                return "trending.rating";
            }
        }
        return sortBy;
    }

}
