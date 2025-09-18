package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Language;
import com.odian.moviesearch.dao.postgres.entity.LanguageEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface LanguageEntityMapper {
    Language entityToDomain (LanguageEntity entity);
    Set<Language> entityToDomain (Set<LanguageEntity> entities);
    LanguageEntity domainToEntity (Language language);
}
