package com.odian.moviesearch.core.model.utils;

public interface SearchCriteria {
    PageCriteria getPageCriteria ();
    SortCriteria getSortCriteria ();
    FilterCriteria getFilterCriteria ();
}
