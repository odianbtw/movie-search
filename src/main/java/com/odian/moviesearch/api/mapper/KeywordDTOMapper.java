package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.KeywordDTO;
import com.odian.moviesearch.core.domain.model.Keyword;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface KeywordDTOMapper {
    @Named("keywordToDto")
    KeywordDTO domainToDto (Keyword keyword);
    Keyword dtoToDomain (KeywordDTO keywordDTO);
    static Keyword dtoToDomainStatic(KeywordDTO keywordDTO) {
        if (keywordDTO == null) return null;
        return new Keyword(keywordDTO.id(), keywordDTO.name());
    }

    static KeywordDTO domainToDtoStatic(Keyword keyword) {
        if (keyword == null) return null;
        return new KeywordDTO(keyword.getId(), keyword.getName());
    }
}
