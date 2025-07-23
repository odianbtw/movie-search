package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.MovieInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpringDataMovieRepository extends JpaRepository<MovieInfoEntity, Long> {
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
    Optional<MovieInfoEntity> findById(@Param("id") Long id);
}
