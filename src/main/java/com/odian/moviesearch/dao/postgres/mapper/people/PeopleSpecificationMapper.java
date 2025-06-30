package com.odian.moviesearch.dao.postgres.mapper.people;

import com.odian.moviesearch.core.model.utils.Parameter;
import com.odian.moviesearch.dao.postgres.model.CountryEntity;
import com.odian.moviesearch.dao.postgres.model.PersonEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PeopleSpecificationMapper {
    default Specification<PersonEntity> to (List<Parameter> parameterList) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            for (var param : parameterList) {
                switch (param.name()) {
                    case "name" -> {
                        var name = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), param.value() + "%");
                        predicateList.add(name);
                    }
                    case "countryId" -> {
                        Join<PersonEntity, CountryEntity> countryJoin = root.join("country");
                        var country = criteriaBuilder.equal(countryJoin.get("id"), Long.valueOf(param.value()));
                        predicateList.add(country);
                    }
                    default -> throw new IllegalArgumentException("Unknown field");
                }
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
