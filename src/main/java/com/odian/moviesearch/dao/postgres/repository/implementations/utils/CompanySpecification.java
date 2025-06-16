package com.odian.moviesearch.dao.postgres.repository.implementations.utils;

import com.odian.moviesearch.core.model.utils.FilterCriteria;
import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CompanySpecification {

    public static Specification<CompanyEntity> fromFilter (FilterCriteria filterCriteria) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            for (var parameter : filterCriteria.parameters()) {
                if (parameter.name().equals("name")) {
                    String pattern = "%" + parameter.value() + "%";
                    Predicate startsWith = criteriaBuilder.like(root.get("name"), parameter.value() + "%");
                    Predicate contains = criteriaBuilder.like(root.get("name"), pattern);
                    predicateList.add(criteriaBuilder.or(startsWith, contains));
                } else {
                    throw new IllegalArgumentException("Unknown filter: " + parameter.name());
                }
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        });
    }
}
