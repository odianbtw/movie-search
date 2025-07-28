package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.MovieInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface SpringDataMovieRepository extends JpaRepository<MovieInfoEntity, Long>, JpaSpecificationExecutor<MovieInfoEntity> {
    @Query(
            """
            select distinct m from MovieInfoEntity m 
            left join fetch m.countries 
            left join fetch m.genres 
            left join fetch m.companies 
            left join fetch m.score 
            left join fetch m.medias 
            where m.id = :id
            """
    )
    Optional<MovieInfoEntity> findByIdWithDetails(@Param("id") Long id);
    @EntityGraph(attributePaths = {"score"})
    Page<MovieInfoEntity> findAll(@Nullable Specification<MovieInfoEntity> spec, Pageable pageable);
    @Query("SELECT m FROM MovieInfoEntity m LEFT JOIN FETCH m.medias LEFT JOIN FETCH m.score WHERE m IN :movies")
    List<MovieInfoEntity> findAllByMovies(@Param("movies") List<MovieInfoEntity> movies);
}
