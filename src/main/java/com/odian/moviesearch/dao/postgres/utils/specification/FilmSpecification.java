package com.odian.moviesearch.dao.postgres.utils.specification;

import com.odian.moviesearch.dao.postgres.entity.FilmEntity;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class FilmSpecification {


    public Specification<FilmEntity> findByIdSpecification () {
        return ((root, query, criteriaBuilder) -> {
            root.fetch("essentialMedia", JoinType.LEFT);
            root.fetch("genres", JoinType.LEFT);
            root.fetch("countries", JoinType.LEFT);
            root.fetch("studios", JoinType.LEFT);
            root.fetch("languages", JoinType.LEFT);
            root.fetch("keywords", JoinType.LEFT);
            root.fetch("ratings", JoinType.LEFT);
            root.fetch("popularity", JoinType.LEFT);
            root.fetch("trending", JoinType.LEFT);


            var contributionsJoin = root.fetch("filmContributions", JoinType.LEFT);
            contributionsJoin.on(criteriaBuilder.equal(
                    contributionsJoin.get("contributionType"),
                    ContributionType.DIRECTOR
            ));
            query.distinct(true);
            return null;
        });
    }
}
