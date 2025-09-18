package com.odian.moviesearch.api.util;

import com.odian.moviesearch.core.application.model.Order;
import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.RequestParameter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

public class RequestCriteriaBuilder {

    public static Pageable getPageable (HttpServletRequest request) {
        String s = request.getParameter("pageSize");
        Integer pageSize = Integer.valueOf(s == null ? "20" : s);
        String p = request.getParameter("page");
        Integer page = Integer.valueOf(p == null ? "0" : p);
        return new Pageable(pageSize, page);
    }

    public static Order getOrder (HttpServletRequest request) {
        String input = request.getParameter("order");
        return Order.valueOf(input == null ? "ASC" : input.toUpperCase());
    }

    public static String getSortBy (HttpServletRequest request) {
        String input = request.getParameter("sortBy");
        return input == null ? "name" : input;
    }

    public static Set<RequestParameter> getRequestParameters (HttpServletRequest request) {
        final Set<String> parameterNamesToIgnore = Set.of("pageSize", "page", "order", "sortBy");
        var requestParams = new HashSet<RequestParameter>();
        for (var entry : request.getParameterMap().entrySet()) {
            if (parameterNamesToIgnore.contains(entry.getKey())) {
                continue;
            } else {
                requestParams.add(new RequestParameter(entry.getKey(), entry.getValue()[0]));
            }
        }
        return requestParams;
    }

}
