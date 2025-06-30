package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.PagedResponseDTO;
import com.odian.moviesearch.api.model.PersonDTO;
import com.odian.moviesearch.api.model.PersonItemDTO;
import com.odian.moviesearch.api.model.PersonRequest;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.Person;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
    uses = {CountryDTOMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PeopleDTOMapper {
    @Mapping(target = "country.id", source = "countryId")
    @Mapping(target = "photo.url", source = "photoUrl")
    @Mapping(target = "photo.mediaType", expression = "java(com.odian.moviesearch.core.model.enums.MediaType.PHOTO)")
    Person to (PersonRequest person);
    @Mapping(target = "photoUrl", source = "photo.url")
    PersonDTO to (Person person);
    @Mapping(target = "photoUrl", source = "photo.url")
    PersonItemDTO map (Person person);
    List<PersonItemDTO> map (List<Person> people);
    PagedResponseDTO<PersonItemDTO> to (PagedResponse<Person> people);
}
