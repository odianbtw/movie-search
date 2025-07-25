package com.odian.moviesearch.api.util;

import com.odian.moviesearch.core.application.model.Order;
import com.odian.moviesearch.core.application.model.Pageable;
import com.odian.moviesearch.core.application.model.Parameter;
import com.odian.moviesearch.core.application.model.Sortable;
import jakarta.servlet.http.HttpServletRequest;

public class PageableBuilder {

    public static Pageable fromHttpRequest(HttpServletRequest request) {
        Pageable pageable = new Pageable();
        var paramMap = request.getParameterMap();

        for (var entry : paramMap.entrySet()) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            String first = values.length > 0 ? values[0] : null;

            switch (name.toLowerCase()) {
                case "size" -> {
                    if (first != null) {
                        try {
                            pageable.setPageSize(Long.parseLong(first));
                        } catch (NumberFormatException ignored) { }
                    }
                }
                case "page" -> {
                    if (first != null) {
                        try {
                            pageable.setCurrentPage(Integer.parseInt(first));
                        } catch (NumberFormatException ignored) { }
                    }
                }
                case "sortby" -> {
                    if (pageable.getSortable() == null) {
                        pageable.setSortable(new Sortable());
                    }
                    pageable.getSortable().setSortBy(first);
                }
                case "order" -> {
                    if (pageable.getSortable() == null) {
                        pageable.setSortable(new Sortable());
                    }
                    if (first != null) {
                        try {
                            pageable.getSortable()
                                    .setOrder(Order.valueOf(first.trim().toUpperCase()));
                        } catch (IllegalArgumentException ignored) { }
                    }
                }
                default -> {
                    for (String v : values) {
                        pageable.getParams().add(new Parameter(name, v));
                    }
                }
            }
        }

        return pageable;
    }

}
