package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
public class Person {
    private final Id personId;
    private String name;
    private String biography;
    private Country country;
    private Set<Media> medias;


    public Optional<Media> getPortrait () {
        return medias.stream()
                .filter(media -> media.getMediaType() == MediaType.PORTRAIT)
                .findFirst();
    }
}
