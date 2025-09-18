package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.port.in.MediaService;
import com.odian.moviesearch.core.application.port.out.MediaRepository;
import com.odian.moviesearch.core.domain.model.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultMediaService implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    @Transactional
    public Set<Media> saveAll (Set<Media> medias) {
        medias.forEach(m -> m.setId(UUID.randomUUID()));
        return mediaRepository.saveAll(medias);
    }

    @Override
    public Media save(Media media) {
        media.setId(UUID.randomUUID());
        return mediaRepository.save(media);
    }
}
