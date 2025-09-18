package com.odian.moviesearch.core.application.model;

import java.util.List;
import java.util.Set;

public interface RequestCriteria {
    Pageable getPageable ();
    void setPageable (Pageable pageable);
    Order getOrder ();
    void setOrder (Order order);
    String getSortBy ();
    void setSortBy (String sortBy);
    Set<RequestParameter> getRequestParameters ();
    void setRequestParameters (Set<RequestParameter> requestParameters);
}
