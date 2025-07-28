package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.MovieCastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMovieCreditsRepository extends JpaRepository<MovieCastEntity, MovieCastEntity.Key> {
}
