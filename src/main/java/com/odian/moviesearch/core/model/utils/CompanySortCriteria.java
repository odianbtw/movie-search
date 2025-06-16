package com.odian.moviesearch.core.model.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class CompanySortCriteria implements SortCriteria {
    private final Direction direction;
    private final String sortBy;
    private final Set<String> validParameterNames = Set.of("name");

    @Override
    public boolean canBeSortedBy(String sortBy) {
        return validParameterNames.contains(sortBy);
    }

    @Override
    public String sortBy() {
        return this.sortBy;
    }

    @Override
    public Direction getSortDirection() {
        return this.direction;
    }
}
