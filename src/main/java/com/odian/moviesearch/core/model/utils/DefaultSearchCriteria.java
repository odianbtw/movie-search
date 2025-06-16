package com.odian.moviesearch.core.model.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultSearchCriteria implements SearchCriteria{

    private final PageCriteria pageCriteria;
    private final SortCriteria sortCriteria;
    private final FilterCriteria filterCriteria;

    @Override
    public PageCriteria getPageCriteria() {
        return this.pageCriteria;
    }

    @Override
    public SortCriteria getSortCriteria() {
        return this.sortCriteria;
    }

    @Override
    public FilterCriteria getFilterCriteria() {
        return this.filterCriteria;
    }
}
