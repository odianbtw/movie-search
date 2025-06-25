package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.PagedResponseDTO;
import com.odian.moviesearch.api.model.PersonDTO;
import com.odian.moviesearch.api.model.PersonItemDTO;
import com.odian.moviesearch.api.model.PersonRequest;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeopleDTOMapper {
    Person to (PersonRequest person);
    PersonDTO to (Person person);
    PagedResponseDTO<PersonItemDTO> to (PagedResponse<Person> people);
}
