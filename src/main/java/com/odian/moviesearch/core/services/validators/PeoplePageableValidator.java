package com.odian.moviesearch.core.services.validators;

import java.util.Set;

public class PeoplePageableValidator extends PageableValidator {
    @Override
    public Set<String> getValidSortBy() {
        return Set.of();
    }

    @Override
    public Set<String> getValidParameterNames() {
        return Set.of();
    }
}
