package com.odian.moviesearch.dao.postgres.mapper.company;


import com.odian.moviesearch.core.model.utils.Parameter;
import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import jakarta.persistence.criteria.Predicate;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CompanySpecificationMapper {

    default Specification<CompanyEntity> to (List<Parameter> parameters) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            for (var parameter : parameters) {
                if (parameter.name().equals("name")) {
                    var name = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), parameter.value() + "%");
                    predicateList.add(name);
                } else {
                    throw new IllegalArgumentException("Unknown field");
                }
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        });
    }
}
