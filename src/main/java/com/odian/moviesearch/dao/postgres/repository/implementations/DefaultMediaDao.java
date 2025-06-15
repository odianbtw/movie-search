package com.odian.moviesearch.dao.postgres.repository.implementations;

import com.odian.moviesearch.core.dao.MediaDao;
import com.odian.moviesearch.core.exceptions.DaoException;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultMediaDao implements MediaDao {

    private final MediaRepository repository;
    private final MediaEntityMapper mapper;

    @Override
    public Media create(Media media) {
        try {
            return mapper.map(
                    repository.save(mapper.to(media))
            );
        } catch (Exception e) {
            throw new DaoException("Unexpected error occurred during connection to the database");
        }
    }
}
