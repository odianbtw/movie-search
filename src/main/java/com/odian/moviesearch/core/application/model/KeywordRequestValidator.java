package com.odian.moviesearch.core.application.model;

import java.util.Set;

public class KeywordRequestValidator implements RequestValidator {

    private final Set<String> validSortBy = Set.of("name");
    private final Set<String> validRequestParameterNames = Set.of("name");

    @Override
    public void validateSortBy(String sortBy) {
        if (!validSortBy.contains(sortBy))
            throw new IllegalArgumentException("Cannot sort keywords by - " + sortBy);
    }

    @Override
    public void validateRequestParameters(Set<RequestParameter> requestParameters) {
        for (var param : requestParameters) {
            if (!validRequestParameterNames.contains(param.name()))
                throw new IllegalArgumentException("Cannot use request param - " + param.name());
        }
    }
}
