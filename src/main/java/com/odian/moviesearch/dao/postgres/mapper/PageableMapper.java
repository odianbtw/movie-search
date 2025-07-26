package com.odian.moviesearch.dao.postgres.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface PageableMapper {
    default Pageable springPageableFromCustom (com.odian.moviesearch.core.application.model.Pageable customPageable) {
        return PageRequest.of(
                customPageable.getCurrentPage(),
                (int) customPageable.getPageSize(),
                Sort.by(
                        Sort.Direction.fromString(customPageable.getSortable().getOrder().name()),
                        (Objects.equals(customPageable.getSortable().getSortBy(), "score")) ? "score.score" : customPageable.getSortable().getSortBy()
                )
        );
    }
}
