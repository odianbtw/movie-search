package com.odian.moviesearch.dao.postgres.repositories.spring;

import com.odian.moviesearch.dao.postgres.model.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
}
