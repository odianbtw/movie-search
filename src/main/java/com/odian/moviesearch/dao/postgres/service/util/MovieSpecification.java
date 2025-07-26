package com.odian.moviesearch.dao.postgres.service.util;

import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.dao.postgres.entity.MovieInfoEntity;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {


    public Specification<MovieInfoEntity> fromPageable(Pageable pageable) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            for (var param : pageable.getParams()) {
                String name  = param.name();
                String value = param.value();

                switch (name) {
                    case "name" -> {
                        predicates.add(
                                cb.like(
                                        cb.lower(root.get("title")),
                                        value.toLowerCase() + "%"
                                )
                        );
                    }
                    case "genreId" -> {
                        var join = root.joinSet("genres", JoinType.INNER);
                        predicates.add(cb.equal(join.get("id"), Integer.valueOf(value)));
                    }
                    case "countryId" -> {
                        var join = root.joinSet("countries", JoinType.INNER);
                        predicates.add(cb.equal(join.get("id"), Integer.valueOf(value)));
                    }
                    case "scoreFrom" -> {
                        var join = root.join("score", JoinType.INNER);
                        predicates.add(cb.greaterThanOrEqualTo(join.get("score"), Float.valueOf(value)));
                    }
                    case "scoreTo" -> {
                        var join = root.join("score", JoinType.INNER);
                        predicates.add(cb.lessThanOrEqualTo(join.get("score"), Float.valueOf(value)));
                    }
                    case "yearFrom" -> {
                        int y = Integer.parseInt(value);
                        LocalDate fromDate = LocalDate.of(y, 1, 1);
                        predicates.add(cb.greaterThanOrEqualTo(root.get("releaseDate"), fromDate));
                    }
                    case "yearTo" -> {
                        int y = Integer.parseInt(value);
                        LocalDate toDate = LocalDate.of(y, 12, 31);
                        predicates.add(cb.lessThanOrEqualTo(root.get("releaseDate"), toDate));
                    }
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


}
