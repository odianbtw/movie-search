package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.MediaRepository;
import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.dao.postgres.entity.MediaEntity;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataMediaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DefaultMediaRepository implements MediaRepository {

//    private final SpringDataMediaRepository mediaRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    private final MediaEntityMapper mediaEntityMapper;

    @Override
    public Set<Media> saveAll(Set<Media> medias) {
        var entities = mediaEntityMapper.domainToEntity(medias);
        entities.forEach(entityManager::persist);
        return entities
                .stream()
                .map(mediaEntityMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Media save(Media media) {
        var entity = mediaEntityMapper.domainToEntity(media);
        entityManager.persist(entity);
        return mediaEntityMapper.entityToDomain(entity);
    }
}
