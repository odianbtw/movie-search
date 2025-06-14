package com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories;

import com.odian.moviesearch.dao.postgres.model.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
}
