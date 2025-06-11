package com.odian.moviesearch.dao.mysql.repositories.interfaces;

import com.odian.moviesearch.dao.mysql.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query("""
        from MovieEntity m 
        left join fetch m.genres 
        left join fetch m.filmedAt 
        left join fetch m.productionStudios 
        left join fetch m.movieScore 
        left join fetch m.medias
        where m.id = :id
    """)
    Optional<MovieEntity> findByIdWithDetails(Long id);

    List<MovieEntity> findMoviesByNameStartsWith(String name);
}
