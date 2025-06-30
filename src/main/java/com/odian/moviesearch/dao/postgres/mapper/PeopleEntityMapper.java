package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.model.Person;
import com.odian.moviesearch.dao.postgres.model.PersonEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
    uses = {CountryEntityMapper.class, MediaEntityMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PeopleEntityMapper {
    PersonEntity to (Person person);
    Person to (PersonEntity person);
    @Named("mapToItem")
    @Mapping(target = "photo.url", source = "photo.url")
    @Mapping(target = "country", ignore = true)
    Person map (PersonEntity person);
}
