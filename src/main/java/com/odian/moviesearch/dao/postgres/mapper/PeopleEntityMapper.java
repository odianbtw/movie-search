package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.model.Person;
import com.odian.moviesearch.dao.postgres.model.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PeopleEntityMapper {
    PersonEntity to (Person person);
    Person to (PersonEntity person);
}
