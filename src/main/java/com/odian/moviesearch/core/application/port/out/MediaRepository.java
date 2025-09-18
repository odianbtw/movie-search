package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.Media;

import java.util.Set;

public interface MediaRepository {
    Set<Media> saveAll (Set<Media> medias);
    Media save(Media media);
}
