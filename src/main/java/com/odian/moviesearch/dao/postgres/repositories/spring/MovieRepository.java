package com.odian.moviesearch.dao.postgres.repositories.spring;

import com.odian.moviesearch.dao.postgres.model.MovieEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {
    @EntityGraph(attributePaths = {"genres", "countries", "companies.logo", "medias", "companies.country"})
    Optional<MovieEntity> findById(Long id);
    @EntityGraph(attributePaths = {"medias", "score"})
    Page<MovieEntity> findAll(Specification<MovieEntity> spec, Pageable pageable);
    @Query("from MovieEntity m where m.id = :id")
    Optional<MovieEntity> findByIdNotFull (@Param("id") Long id);
}
