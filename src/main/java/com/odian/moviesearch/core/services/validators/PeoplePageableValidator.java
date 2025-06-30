package com.odian.moviesearch.core.services.validators;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PeoplePageableValidator extends PageableValidator {
    @Override
    public Set<String> getValidSortBy() {
        return Set.of("name");
    }

    @Override
    public Set<String> getValidParameterNames() {
        return Set.of("name", "countryId");
    }
}
