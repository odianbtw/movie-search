package com.odian.moviesearch.dao.postgres.repositories.spring;


import com.odian.moviesearch.dao.postgres.model.MovieCreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCreditRepository extends JpaRepository<MovieCreditEntity, Long> {
}
