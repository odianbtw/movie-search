package com.odian.moviesearch.api.utils;

import com.odian.moviesearch.core.model.utils.Order;
import com.odian.moviesearch.core.model.utils.Pageable;
import com.odian.moviesearch.core.model.utils.Parameter;
import com.odian.moviesearch.core.model.utils.Sortable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PageableBinder {

    public Pageable pageableFromRequest (HttpServletRequest request) {
        Pageable pageable = new Pageable();
        Sortable sortable = new Sortable();
        List<Parameter> parameters = new ArrayList<>();
        for (var entry : request.getParameterMap().entrySet()) {
            switch (entry.getKey()) {
                case "size" -> {
                    try {
                        int size = Integer.parseInt(entry.getValue()[0]);
                        pageable.setPageSize(size);
                    } catch (NumberFormatException e) {
                        pageable.setPageSize(20);
                    }
                }
                case "page" -> {
                    try {
                        int page = Integer.parseInt(entry.getValue()[0]);
                        pageable.setCurrentPage(page);
                    } catch (NumberFormatException e) {
                        pageable.setCurrentPage(20);
                    }
                }
                case "order" -> {
                    var order = Order.fromString(entry.getValue()[0]);
                    sortable.setOrder(order);
                }
                case "sort" -> {
                    if (Objects.equals(entry.getValue()[0], "score"))
                        sortable.setSortBy(entry.getValue()[0] + ".score");
                    else
                        sortable.setSortBy(entry.getValue()[0]);

                }
                default -> {
                    for (var value: entry.getValue()) {
                        Parameter parameter = new Parameter(entry.getKey(), value);
                        parameters.add(parameter);
                    }
                }
            }
        }
        pageable.setSortable(sortable);
        pageable.setParameters(parameters);
        return pageable;
    }

}
