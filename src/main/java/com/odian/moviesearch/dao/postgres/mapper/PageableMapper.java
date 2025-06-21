package com.odian.moviesearch.dao.postgres.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper
public interface PageableMapper {

    default Pageable to (com.odian.moviesearch.core.model.utils.Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getCurrentPage();

        var sort = Sort.by(Sort.Direction.fromString(
                pageable.getSortable().getOrder().name()
        ), pageable.getSortable().getSortBy());
        return PageRequest.of(currentPage, pageSize, sort);
    }
}
