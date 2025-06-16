package com.odian.moviesearch.core.model.utils;



public interface SortCriteria {

    boolean canBeSortedBy (String sortBy);
    String sortBy();
    Direction getSortDirection();

}
