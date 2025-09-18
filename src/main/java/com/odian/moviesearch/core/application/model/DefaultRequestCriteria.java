package com.odian.moviesearch.core.application.model;

import lombok.RequiredArgsConstructor;


import java.util.Set;

@RequiredArgsConstructor
public class DefaultRequestCriteria implements RequestCriteria {

    private final RequestValidator requestValidator;
    private Pageable pageable;
    private Order order;
    private String sortBy;
    private Set<RequestParameter> requestParameters;


    @Override
    public Pageable getPageable() {
        return pageable;
    }

    @Override
    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String getSortBy() {
        return sortBy;
    }

    @Override
    public void setSortBy(String sortBy) {
        requestValidator.validateSortBy(sortBy);
        this.sortBy = sortBy;
    }

    @Override
    public Set<RequestParameter> getRequestParameters() {
        return requestParameters;
    }

    @Override
    public void setRequestParameters(Set<RequestParameter> requestParameters) {
        requestValidator.validateRequestParameters(requestParameters);
        this.requestParameters = requestParameters;
    }
}
