package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Keyword;
import com.odian.moviesearch.dao.postgres.entity.KeywordEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface KeywordEntityMapper {
    Keyword entityToDomain (KeywordEntity entity);
    Set<Keyword> entityToDomain (Set<KeywordEntity> entities);
}
