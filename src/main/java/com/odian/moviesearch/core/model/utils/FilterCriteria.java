package com.odian.moviesearch.core.model.utils;

import java.util.List;

public interface FilterCriteria {
    boolean canBeFilteredBy(Parameter parameter);
    List<Parameter> parameters();
}
