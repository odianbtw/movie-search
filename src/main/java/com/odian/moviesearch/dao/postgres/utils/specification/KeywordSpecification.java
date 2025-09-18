package com.odian.moviesearch.dao.postgres.utils.specification;

import com.odian.moviesearch.core.application.model.RequestCriteria;
import com.odian.moviesearch.dao.postgres.entity.KeywordEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class KeywordSpecification {
    public Specification<KeywordEntity> findAll(RequestCriteria requestCriteria) {
        return (root, query, cb) -> {
            Predicate predicate = null;
            for (var param : requestCriteria.getRequestParameters()) {
                if (Objects.equals(param.name(), "name")) {
                    predicate = cb.like(cb.lower(root.get("name")), param.value().toLowerCase() + "%");
                }
            }
            query.distinct(true);
            return predicate;
        };
    }
}
