package com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories;

import com.odian.moviesearch.dao.postgres.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
}
