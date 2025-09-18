package com.odian.moviesearch.dao.postgres.entity;

import com.odian.moviesearch.core.domain.model.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "essential_film_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EssentialFilmMediaEntity {

    @Id
    @Column(name = "film_id")
    private UUID filmId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_id")
    private MediaEntity poster;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "backdrop_id")
    private MediaEntity backdrop;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trailer_id")
    private MediaEntity trailer;


    public void setMedias (Set<MediaEntity> medias) {
        setPoster(
                medias
                        .stream()
                        .filter(m -> m.getMediaType() == MediaType.POSTER)
                        .findFirst().orElse(null)
        );
        setBackdrop(
                medias
                        .stream()
                        .filter(m -> m.getMediaType() == MediaType.BACKDROP)
                        .findFirst().orElse(null)
        );
        setTrailer(
                medias
                        .stream()
                        .filter(m -> m.getMediaType() == MediaType.TRAILER)
                        .findFirst().orElse(null)
        );
    }
}
