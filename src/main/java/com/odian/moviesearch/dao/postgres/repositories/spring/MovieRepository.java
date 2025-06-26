package com.odian.moviesearch.dao.postgres.repositories.spring;

import com.odian.moviesearch.core.model.enums.MovieRating;
import com.odian.moviesearch.dao.postgres.model.MovieEntity;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

@SqlResultSetMapping(name = "OneMovieMapping",
    classes = @ConstructorResult(
            targetClass = MovieEntity.class,
            columns = {
                    @ColumnResult(name = "id", type = Long.class),
                    @ColumnResult(name = "name", type = String.class),
                    @ColumnResult(name = "slogan", type = String.class),
                    @ColumnResult(name = "description", type = String.class),
                    @ColumnResult(name = "releaseDate", type = LocalDate.class),
                    @ColumnResult(name = "durationTime", type = Integer.class),
                    @ColumnResult(name = "budget", type = Integer.class),
                    @ColumnResult(name = "revenue", type = Integer.class),
                    @ColumnResult(name = "movieRating", type = MovieRating.class),


            }
    )
)
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

    @Query
    Optional<MovieEntity> findById(@Param("id") Long id);
}
