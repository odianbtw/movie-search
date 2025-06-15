package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.CompanyDTO;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.core.model.Company;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = CountryDTOMapper.class)
public interface CompanyDTOMapper {
    @Mapping(target = "country.id", source = "countryId")
    @Mapping(target = "media.url", source = "logoUrl")
    Company to (CompanyRequest company);
    @Mapping(target = "logoUrl", source = "media.url")
    CompanyDTO to (Company company);
}
