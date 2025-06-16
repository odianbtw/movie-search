package com.odian.moviesearch.core.model.utils;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CompanyFilterCriteria implements FilterCriteria {
    private final List<Parameter> filterParameters;
    private final Set<String> validParameterNames = Set.of("name");
    @Override
    public boolean canBeFilteredBy(Parameter parameter) {
        return validParameterNames.contains(parameter.name());
    }

    @Override
    public List<Parameter> parameters() {
        return this.filterParameters;
    }
}
