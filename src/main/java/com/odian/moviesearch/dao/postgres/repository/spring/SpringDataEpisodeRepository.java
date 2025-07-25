package com.odian.moviesearch.dao.postgres.repository.spring;

import com.odian.moviesearch.core.domain.model.TitleType;
import com.odian.moviesearch.dao.postgres.entity.SeriesContentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SpringDataEpisodeRepository extends JpaRepository<SeriesContentEntity, Long> {
    @EntityGraph(attributePaths = {"score", "medias"})
    Optional<SeriesContentEntity> findById(Long id);
    @Query("from SeriesContentEntity se left join fetch se.medias left join fetch se.score where se.series.id = :id and se.seasonNumber = :seasonNumber")
    List<SeriesContentEntity> findSeasonBySeriesId(@Param("id") Long id, @Param("seasonNumber") Integer seasonNumber);
    @Query("SELECT DISTINCT sc.seasonNumber FROM SeriesContentEntity sc WHERE sc.series.id = :id")
    Set<Integer> countDistinctSeasonNumberBySeriesId (@Param("id") Long id);
}
