package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.LanguageDTO;
import com.odian.moviesearch.core.domain.model.Language;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageDTOMapper {
    LanguageDTO domainToDto (Language language);
    Language idToDomain (Integer id);
}
