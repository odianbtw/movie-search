package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.TitleCreditRequest;
import com.odian.moviesearch.core.domain.model.TitleCredit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TitleCrewDTOMapper {
    @Mapping(target = "person.personId.id", source = "personId")
    @Mapping(target = "title.titleId.id", source = "titleId")
    TitleCredit dtoRequestToDomain (TitleCreditRequest request);
}
