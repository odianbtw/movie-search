package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.domain.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryDTOMapper {
    CountryDTO domainToDto (Country country);
}
