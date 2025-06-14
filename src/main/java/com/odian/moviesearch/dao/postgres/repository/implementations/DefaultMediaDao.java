package com.odian.moviesearch.dao.postgres.repository.implementations;

import com.odian.moviesearch.core.dao.MediaDao;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.dao.postgres.mapper.MediaMapper;
import com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultMediaDao implements MediaDao {

    private final MediaRepository repository;
    private final MediaMapper mapper;

    @Override
    public Media create(Media media) {
        return mapper.map(
                repository.save(mapper.to(media))
        );
    }
}
