package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.dao.postgres.entity.MovieCastEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TitleCreditEntityMapper {

    @Mapping(target = "movieId", source = "title.titleId.id")
    @Mapping(target = "personId", source = "person.personId.id")
    @Mapping(target = "role", source = "titleRole")
    MovieCastEntity domainToEntityForMovie (TitleCredit movieCredit);
}
