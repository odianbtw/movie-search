package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class PeopleEntityMapper {

    @Autowired protected CountryEntityMapper countryEntityMapper;
    @Autowired protected MediaEntityMapper mediaEntityMapper;

    public PersonEntity domainToEntity (Person person) {
        if (person == null) return null;
        return new PersonEntity(
                null,
                person.getPersonId().imdbId(),
                person.getName(),
                person.getBiography(),
                countryEntityMapper.domainToEntity(person.getCountry()),
                person.getMedias().stream().map(mediaEntityMapper::domainToEntity).collect(Collectors.toSet()),
                null,
                null

        );
    }

    public Person entityToDomain (PersonEntity entity) {
        if (entity == null) return null;
        Id id = new Id(entity.getId(), entity.getImdbId());
        return new Person(
                id,
                entity.getName(),
                entity.getBiography(),
                countryEntityMapper.entityToDomain(entity.getCountry()),
                entity.getMedias().stream().map(mediaEntityMapper::entityToDomain).collect(Collectors.toSet())

        );
    }
}
