package com.odian.moviesearch.core.services.validators;

import com.odian.moviesearch.core.model.utils.Pageable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CompanyPageableValidator implements PageableValidator{
    private final Set<String> validSortBy = Set.of("name");
    private final Set<String> validParameterNames = Set.of("name");

    @Override
    public void validate (Pageable pageable) {
        if (pageable.getPageSize() < 1) throw new IllegalArgumentException("Size of page must me greater than 0");
        if (pageable.getCurrentPage() < 0) throw new IllegalArgumentException("Page must me greater than or equal 0");
        if (!validParameterNames.contains(pageable.getSortable().getSortBy())) throw new IllegalArgumentException("Companies can't be sorted by this parameter");
        for (var parameter: pageable.getParameters()) {
            if (!validParameterNames.contains(parameter.name()))
                throw new IllegalArgumentException(parameter.name() + " parameter doesn't allowed here");
        }
    }
}
