package com.odian.moviesearch.repositories;

import com.odian.moviesearch.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
        from Movie m 
        left join fetch m.genres 
        left join fetch m.filmedAt 
        left join fetch m.productionStudios 
        left join fetch m.movieScore 
        left join fetch m.medias
        where m.id = :id
    """)
    Optional<Movie> findByIdWithDetails(Long id);
}
