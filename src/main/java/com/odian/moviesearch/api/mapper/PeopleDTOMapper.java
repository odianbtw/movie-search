package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.PersonDTO;
import com.odian.moviesearch.api.model.PersonRequest;
import com.odian.moviesearch.core.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class PeopleDTOMapper {

    @Autowired
    protected CountryDTOMapper countryDTOMapper;


    public Person dtoRequestToDomain (PersonRequest request) {
        Id id = new Id(null, request.imdbId());
        return new Person(
                id,
                request.name(),
                request.biography(),
                new Country(request.countryId(), null),
                Set.of(new Media(null, request.photoUrl(), MediaType.PORTRAIT))
        );
    }

    public PersonDTO domainToDto (Person person) {
        return new PersonDTO(
                person.getPersonId().id(),
                person.getPersonId().imdbId(),
                person.getName(),
                person.getBiography(),
                countryDTOMapper.domainToDto(person.getCountry()),
                person.getMedias().stream()
                        .filter(media -> Objects.equals(media.getMediaType(), MediaType.PORTRAIT))
                        .map(Media::getMediaUri)
                        .findFirst().orElse(null)
        );
    }

}
