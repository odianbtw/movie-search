package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.NamedPersonItemDTO;
import com.odian.moviesearch.core.domain.model.Person;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {
    NamedPersonItemDTO domainToItemDto (Person domain);
    Set<NamedPersonItemDTO> domainToItemsDto (Set<Person> domain);
}
