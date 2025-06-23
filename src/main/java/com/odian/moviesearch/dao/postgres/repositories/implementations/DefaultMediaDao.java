package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.MediaDao;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.dao.postgres.repositories.spring.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultMediaDao implements MediaDao {
    private final MediaRepository mediaRepository;
    @Override
    public Media create(Media media) {
        return null;
    }
}
