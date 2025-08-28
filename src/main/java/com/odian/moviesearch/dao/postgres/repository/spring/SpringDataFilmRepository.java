package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataFilmRepository extends JpaRepository<FilmEntity, UUID>, JpaSpecificationExecutor<FilmEntity> {
    @Query("""
        from FilmEntity fe left join fetch fe.essentialMedia 
        left join fetch fe.genres 
        left join fetch fe.countries 
        left join fetch fe.studios 
        left join fetch fe.languages 
        left join fetch fe.keywords 
        left join fetch fe.ratings 
        left join fetch fe.popularity 
        left join fetch fe.trending 
        left join fetch fe.filmContributions 
        where fe.id = :id      
    """)
    Optional<FilmEntity> findByIdWithAllData(@Param("id") UUID id);
}
