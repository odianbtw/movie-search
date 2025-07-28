package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.dao.postgres.entity.MovieInfoEntity;
import com.odian.moviesearch.dao.postgres.entity.SeriesInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataSeriesRepository extends JpaRepository<SeriesInfoEntity, Long>,
        JpaSpecificationExecutor<SeriesInfoEntity> {
    @Query(
            """
            select distinct s from SeriesInfoEntity s 
            left join fetch s.countries 
            left join fetch s.genres 
            left join fetch s.companies 
            left join fetch s.score 
            left join fetch s.medias 
            where s.id = :id
            """
    )
    Optional<SeriesInfoEntity> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT s FROM SeriesInfoEntity s LEFT JOIN FETCH s.medias LEFT JOIN FETCH s.score WHERE s IN :series")
    List<SeriesInfoEntity> findAllBySeries(@Param("series") List<SeriesInfoEntity> series);
}
