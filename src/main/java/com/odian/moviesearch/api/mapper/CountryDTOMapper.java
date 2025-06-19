package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.CountryDTO;
import com.odian.moviesearch.core.model.Country;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CountryDTOMapper {
    CountryDTO to (Country country);
    List<CountryDTO> to (List<Country> countries);

}
