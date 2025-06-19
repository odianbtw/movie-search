package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.CompanyDTO;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.api.model.PagedResponseDTO;
import com.odian.moviesearch.core.model.Company;

import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Media;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.enums.MediaType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = CountryDTOMapper.class)
public interface CompanyDTOMapper {
    @Mapping(target = "country.id", source = "countryId")
    @Mapping(target = "media.url", source = "logoUrl")
    Company to (CompanyRequest company);
    CompanyDTO to (Company company);

}
