package com.odian.moviesearch.core.application.model;

import java.util.List;
import java.util.Set;

public interface RequestValidator {
    void validateSortBy(String sortBy);
    void validateRequestParameters(Set<RequestParameter> requestParameters);
}
