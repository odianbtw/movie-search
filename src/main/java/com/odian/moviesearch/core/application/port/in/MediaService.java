package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.domain.model.Media;

import java.util.Set;

public interface MediaService {
    Set<Media> saveAll (Set<Media> medias);
    Media save (Media media);
}
