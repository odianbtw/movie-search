package com.odian.moviesearch.dao.postgres.mapper;

import com.odian.moviesearch.core.domain.model.Person;
import com.odian.moviesearch.dao.postgres.entity.PersonEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MediaEntityMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonEntityMapper {
    @Mapping(source = "mediaEntity", target = "profilePhoto")
    Person entityToDomain (PersonEntity entity);
}
