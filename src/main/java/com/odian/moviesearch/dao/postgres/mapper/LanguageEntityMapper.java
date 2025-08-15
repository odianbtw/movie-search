package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Language;
import com.odian.moviesearch.dao.postgres.entity.LanguageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageEntityMapper {
    Language entityToDomain (LanguageEntity entity);
}
