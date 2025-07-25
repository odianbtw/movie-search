package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.domain.model.Country;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CountryDTOMapper {
    CountryDTO domainToDto (Country country);
    Set<CountryDTO> domainToDto (Set<Country> country);
}
