package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Person {
    private final UUID id;
    private String slug;
    private String name;
    private String biography;
    private Media profilePhoto;
    private Set<Keyword> keywords;

    public void setProfilePhoto (Media profilePhoto) {
        if (!Objects.equals(profilePhoto.getMediaType(), MediaType.PROFILE))
            throw new IllegalArgumentException("Incorrect media type for profile photo.");
        this.profilePhoto = profilePhoto;
    }
}
