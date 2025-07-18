package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataGenreRepository extends JpaRepository<GenreEntity, Integer> {
}
