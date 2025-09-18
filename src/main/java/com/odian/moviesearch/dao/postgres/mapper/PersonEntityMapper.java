package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.ContributorType;
import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.entity.FilmContributionEntity;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = MediaEntityMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonEntityMapper {
    @Mapping(source = "mediaEntity", target = "profilePhoto")
    Person entityToDomain (PersonEntity entity);
    Set<Person> entityToDomain (Set<PersonEntity> entities);
    default Person entityToDomain (FilmContributionEntity contributionEntity) {
        if (contributionEntity == null) return null;
        if (contributionEntity.getPerson() == null) return null;
        var person = contributionEntity.getPerson();
        return new Person(
                person.getId(),
                person.getSlug(),
                person.getName(),
                person.getBiography(),
                null,
                null
        );
    }

    default Set<Person> entitiesToDomain (Set<FilmContributionEntity> contributionEntity) {
        return contributionEntity.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toSet());
    }

    @Mapping(source = "profilePhoto", target = "mediaEntity")
    PersonEntity domainToEntity (Person person);


    default FilmContributionEntity domainToEntityDirectors (Person person) {
        return new FilmContributionEntity(
                null,
                null,
                domainToEntity(person),
                ContributorType.DIRECTOR,
                null,
                null,
                null,
                null
        );
    }

    Set<FilmContributionEntity> domainToEntityDirectors (Set<Person> people);
}
