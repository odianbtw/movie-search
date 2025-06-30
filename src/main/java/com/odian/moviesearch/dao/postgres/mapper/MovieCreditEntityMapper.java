package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.model.MovieCredit;
import com.odian.moviesearch.dao.postgres.model.MovieCreditEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
    uses = {PeopleEntityMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieCreditEntityMapper {
    @Mapping(target = "person.id", source = "person.id")
    @Mapping(target = "movie", ignore = true)
    MovieCreditEntity to (MovieCredit movieCredit);
    @Mapping(target = "person", source = "person", qualifiedByName = "mapToItem")
    MovieCredit to (MovieCreditEntity entity);
}
