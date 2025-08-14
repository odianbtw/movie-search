package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.KeywordDTO;
import com.odian.moviesearch.core.domain.model.Keyword;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeywordDTOMapper {

    KeywordDTO domainToDto (Keyword keyword);
}
