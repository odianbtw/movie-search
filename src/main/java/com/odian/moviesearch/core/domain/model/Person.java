package com.odian.moviesearch.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private UUID id;
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
