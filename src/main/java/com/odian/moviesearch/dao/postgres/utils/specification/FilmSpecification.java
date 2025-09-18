package com.odian.moviesearch.dao.postgres.utils.specification;

import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.core.domain.model.ContributorType;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FilmSpecification {


    public Specification<FilmEntity> findById(UUID filmId) {
        return (root, query, cb) -> {
            var essentialMedia = root.fetch("essentialMedia", JoinType.LEFT);
            var directors =  root.fetch("directors", JoinType.LEFT);
            directors.fetch("person", JoinType.LEFT);
            essentialMedia.fetch("poster", JoinType.LEFT);
            essentialMedia.fetch("backdrop", JoinType.LEFT);
            essentialMedia.fetch("trailer", JoinType.LEFT);

            root.fetch("genres", JoinType.LEFT);
            root.fetch("countries", JoinType.LEFT);
            root.fetch("studios", JoinType.LEFT);
            root.fetch("languages", JoinType.LEFT);
            root.fetch("keywords", JoinType.LEFT);
            root.fetch("ratings", JoinType.LEFT);
            root.fetch("popularity", JoinType.LEFT);
            root.fetch("trending", JoinType.LEFT);

            var predicateId = cb.equal(root.get("id"), filmId);

            return predicateId;
        };
    }

    public Specification<FilmEntity> findByIdFetchAll (UUID filmId) {
        return (root, query, cb) -> {
            var essentialMedia = root.fetch("essentialMedia", JoinType.LEFT);
            var directors =  root.fetch("directors", JoinType.LEFT);
            directors.fetch("person", JoinType.LEFT);
            essentialMedia.fetch("poster", JoinType.LEFT);
            essentialMedia.fetch("backdrop", JoinType.LEFT);
            essentialMedia.fetch("trailer", JoinType.LEFT);
            root.fetch("medias");
            root.fetch("filmContributions");
            root.fetch("genres", JoinType.LEFT);
            root.fetch("countries", JoinType.LEFT);
            root.fetch("studios", JoinType.LEFT);
            root.fetch("languages", JoinType.LEFT);
            root.fetch("keywords", JoinType.LEFT);
            root.fetch("ratings", JoinType.LEFT);
            root.fetch("popularity", JoinType.LEFT);
            root.fetch("trending", JoinType.LEFT);


            var predicateId = cb.equal(root.get("id"), filmId);

            return predicateId;
        };
    }

    public Specification<FilmEntity> findAll(RequestCriteria criteria) {
        return (root, query, cb) -> {
            var params = criteria.getRequestParameters();
            List<Predicate> predicates = new ArrayList<>();
            var essentialMedia = root.fetch("essentialMedia", JoinType.LEFT);
            essentialMedia.fetch("poster", JoinType.LEFT);
            essentialMedia.fetch("backdrop", JoinType.LEFT);
            essentialMedia.fetch("trailer", JoinType.LEFT);
            root.fetch("ratings", JoinType.LEFT);
            root.fetch("popularity", JoinType.LEFT);
            root.fetch("trending", JoinType.LEFT);
            Join<?, ?> ratingsJoin = null;
            Join<?, ?> genreJoin = null;
            Join<?, ?> languageJoin = null;
            Join<?, ?> countryJoin = null;

            for (var param : params) {
                String name = param.name();
                String value = param.value();
                if (value == null || value.isBlank()) continue;

                switch (name) {
                    case "name" -> {
                        predicates.add(cb.like(cb.lower(root.get("name")), value.toLowerCase() + "%"));
                    }

                    case "ratingFrom" -> {
                        try {
                            double v = Double.parseDouble(value);
                            if (ratingsJoin == null) ratingsJoin = root.join("ratings", JoinType.INNER);
                            predicates.add(cb.greaterThanOrEqualTo(ratingsJoin.get("rating").as(Double.class), v));
                        } catch (NumberFormatException ignored) { }
                    }

                    case "ratingTo" -> {
                        try {
                            double v = Double.parseDouble(value);
                            if (ratingsJoin == null) ratingsJoin = root.join("ratings", JoinType.INNER);
                            predicates.add(cb.lessThanOrEqualTo(ratingsJoin.get("rating").as(Double.class), v));
                        } catch (NumberFormatException ignored) { }
                    }

                    case "yearFrom" -> {
                        try {
                            int y = Integer.parseInt(value);
                            LocalDate from = LocalDate.of(y, 1, 1);
                            predicates.add(cb.greaterThanOrEqualTo(root.get("releaseDate").as(LocalDate.class), from));
                        } catch (NumberFormatException ignored) { }
                    }

                    case "yearTo" -> {
                        try {
                            int y = Integer.parseInt(value);
                            LocalDate to = LocalDate.of(y, 12, 31);
                            predicates.add(cb.lessThanOrEqualTo(root.get("releaseDate").as(LocalDate.class), to));
                        } catch (NumberFormatException ignored) { }
                    }

                    case "genreId" -> {
                        try {
                            Integer id = Integer.parseInt(value);
                            if (genreJoin == null) genreJoin = root.join("genres", JoinType.INNER);
                            predicates.add(cb.equal(genreJoin.get("id"), id));
                        } catch (IllegalArgumentException ignored) { }
                    }

                    case "languageId" -> {
                        try {
                            Integer id = Integer.parseInt(value);
                            if (languageJoin == null) languageJoin = root.join("languages", JoinType.INNER);
                            predicates.add(cb.equal(languageJoin.get("id"), id));
                        } catch (IllegalArgumentException ignored) { }
                    }

                    case "countryId" -> {
                        try {
                            Integer id = Integer.parseInt(value);
                            if (countryJoin == null) countryJoin = root.join("countries", JoinType.INNER);
                            predicates.add(cb.equal(countryJoin.get("id"), id));
                        } catch (IllegalArgumentException ignored) { }
                    }

                }
            }

            if (!predicates.isEmpty()) {
                query.distinct(true);
                return cb.and(predicates.toArray(new Predicate[0]));
            }

            return cb.conjunction();
        };
    }
}
