package com.odian.moviesearch.dao.postgres.repositories.spring;

import com.odian.moviesearch.dao.postgres.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
