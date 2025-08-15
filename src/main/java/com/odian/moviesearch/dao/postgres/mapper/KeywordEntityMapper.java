package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Keyword;
import com.odian.moviesearch.dao.postgres.entity.KeywordEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeywordEntityMapper {
    Keyword entityToDomain (KeywordEntity entity);
}
