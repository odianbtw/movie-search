package com.odian.moviesearch.core.services.validators;

import com.odian.moviesearch.core.model.utils.Pageable;

import java.util.Set;

public abstract class PageableValidator implements Validator<Pageable> {
    @Override
    public void validate(Pageable pageable) {
        if (pageable.getPageSize() < 1) throw new IllegalArgumentException("Size of page must me greater than 0");
        if (pageable.getCurrentPage() < 0) throw new IllegalArgumentException("Page must me greater than or equal 0");
        if (!getValidSortBy().contains(pageable.getSortable().getSortBy())) throw new IllegalArgumentException("Companies can't be sorted by this parameter");
        for (var parameter: pageable.getParameters()) {
            if (!getValidParameterNames().contains(parameter.name()))
                throw new IllegalArgumentException(parameter.name() + " parameter doesn't allowed here");
        }
    }

    public abstract Set<String> getValidSortBy ();
    public abstract Set<String> getValidParameterNames ();
}
