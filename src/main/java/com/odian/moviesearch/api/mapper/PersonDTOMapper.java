package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.NamedPersonItemDTO;
import com.odian.moviesearch.api.model.PersonCreateRequest;
import com.odian.moviesearch.api.model.PersonDTO;
import com.odian.moviesearch.core.domain.model.Media;
import com.odian.moviesearch.core.domain.model.MediaType;
import com.odian.moviesearch.core.domain.model.Person;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {
    NamedPersonItemDTO domainToItemDto (Person domain);
    Set<NamedPersonItemDTO> domainToItemsDto (Set<Person> domain);
    default Person dtoToDomain (PersonCreateRequest request) {
        return new Person(
                null,
                null,
                request.name(),
                request.biography(),
                new Media(null, request.photoUrl(), MediaType.PROFILE),
                request.keywords()
                        .stream()
                        .map(KeywordDTOMapper::dtoToDomainStatic)
                        .collect(Collectors.toSet())
        );
    }

    default PersonDTO domainToDto (Person person) {
        return new PersonDTO(
                person.getId(),
                person.getSlug(),
                person.getName(),
                person.getBiography(),
                (person.getProfilePhoto() == null) ? null : person.getProfilePhoto().getUrl(),
                person.getKeywords()
                        .stream()
                        .map(KeywordDTOMapper::domainToDtoStatic)
                        .collect(Collectors.toSet())
        );
    }

    static Person createById (UUID id) {
        return Person.builder()
                .id(id)
                .build();
    }
}
