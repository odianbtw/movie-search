package com.odian.moviesearch.dao.postgres.mapper.movie;

import com.odian.moviesearch.core.model.utils.Parameter;
import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import com.odian.moviesearch.dao.postgres.model.GenreEntity;
import com.odian.moviesearch.dao.postgres.model.MovieEntity;
import com.odian.moviesearch.dao.postgres.model.MovieScoreEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface MovieSpecificationMapper {
    default Specification<MovieEntity> to (List<Parameter> parameterList) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            for (var param : parameterList) {
                switch (param.name()) {
                    case "name" -> {
                        var name = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), param.value() + "%");
                        predicateList.add(name);
                    }
                    case "genreId" -> {
                        Join<MovieEntity, GenreEntity> genreJoin = root.join("genres");
                        Predicate genrePredicate = criteriaBuilder.equal(genreJoin.get("id"), Long.valueOf(param.value()));
                        predicateList.add(genrePredicate);
                    }
                    case "countryId" -> {
                        Join<MovieEntity, CountryEntity> countryJoin = root.join("countries");
                        Predicate countryPredicate = criteriaBuilder.equal(countryJoin.get("id"), Long.valueOf(param.value()));
                        predicateList.add(countryPredicate);
                    }
                    case "scoreFrom" -> {
                        Join<MovieEntity, MovieScoreEntity> scoreJoin = root.join("movieScore");
                        Predicate scorePredicate = criteriaBuilder.greaterThanOrEqualTo(
                                scoreJoin.get("score"),
                                Float.valueOf(param.value())
                        );
                        predicateList.add(scorePredicate);
                    }
                    case "scoreTo" -> {
                        Join<MovieEntity, MovieScoreEntity> scoreJoin = root.join("movieScore");
                        Predicate scorePredicate = criteriaBuilder.lessThanOrEqualTo(
                                scoreJoin.get("score"),
                                Float.valueOf(param.value())
                        );
                        predicateList.add(scorePredicate);
                    }
                    case "yearFrom" -> {
                        int year = Integer.parseInt(param.value());
                        LocalDate date = LocalDate.of(year, 1, 1);
                        Predicate yearPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("releaseDate"), date);
                        predicateList.add(yearPredicate);
                    }
                    case "yearTo" -> {
                        int year = Integer.parseInt(param.value());
                        LocalDate date = LocalDate.of(year, 1, 1);
                        Predicate yearPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("releaseDate"), date);
                        predicateList.add(yearPredicate);
                    }
                    default -> throw new IllegalArgumentException("Unknown field");
                }

            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
