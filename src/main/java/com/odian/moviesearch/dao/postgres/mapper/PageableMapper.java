package com.odian.moviesearch.dao.postgres.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageableMapper {
    default Pageable springPageableFromCustom (com.odian.moviesearch.core.application.model.Pageable customPageable) {
        PageRequest.of()
    }
}
