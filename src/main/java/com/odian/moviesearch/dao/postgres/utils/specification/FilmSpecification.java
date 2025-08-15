package com.odian.moviesearch.dao.postgres.utils.specification;

import com.odian.moviesearch.core.domain.model.ContributorType;
import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class FilmSpecification {


    public Specification<FilmEntity> findByIdSpecification(UUID filmId) {
        return (root, query, criteriaBuilder) -> {
            root.fetch("essentialMedia", JoinType.LEFT);
            root.fetch("genres", JoinType.LEFT);
            root.fetch("countries", JoinType.LEFT);
            root.fetch("studios", JoinType.LEFT);
            root.fetch("languages", JoinType.LEFT);
            root.fetch("keywords", JoinType.LEFT);
            root.fetch("ratings", JoinType.LEFT);
            root.fetch("popularity", JoinType.LEFT);
            root.fetch("trending", JoinType.LEFT);
            var contributionJoin = root.joinSet("filmContributions", JoinType.LEFT);

            var predicateDirector = criteriaBuilder.equal(
                    contributionJoin.get("contributionType"), ContributorType.DIRECTOR);

            var predicateId = criteriaBuilder.equal(root.get("id"), filmId);

            query.distinct(true);
            return criteriaBuilder.and(predicateId, predicateDirector);
        };
    }

}
