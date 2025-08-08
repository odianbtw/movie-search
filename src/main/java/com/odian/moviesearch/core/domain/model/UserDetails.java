package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDetails {
    private Set<Film> favouriteFilms;
    private Set<Media> medias;

    public Optional<Media> getAvatar () {
        return medias.stream()
                .filter(t -> Objects.equals(t.getMediaType(), MediaType.AVATAR))
                .findFirst();
    }

    public Optional<Media> getBackdrop () {
        return medias.stream()
                .filter(t -> Objects.equals(t.getMediaType(), MediaType.BACKDROP))
                .findFirst();
    }
}
