package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.*;
import com.odian.moviesearch.core.model.Company;

import com.odian.moviesearch.core.model.PagedResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = CountryDTOMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyDTOMapper {
    @Mapping(target = "country.id", source = "countryId")
    @Mapping(target = "media.url", source = "logoUrl")
    @Mapping(target = "media.mediaType", expression = "java(com.odian.moviesearch.core.model.enums.MediaType.LOGO)")
    Company to (CompanyRequest company);
    @Mapping(target = "logoUrl", source = "media.url")
    CompanyDTO to (Company company);
    @Mapping(target = "media.url", source = "logoUrl")
    @Mapping(target = "country.id", source = "countryId")
    @Mapping(target = "media.mediaType", expression = "java(com.odian.moviesearch.core.model.enums.MediaType.LOGO)")
    Company to (CompanyUpdateDTO company);
    @Mapping(target = "logoUrl", source = "media.url")
    CompanyItemDTO map (Company company);
    PagedResponseDTO<CompanyItemDTO> to (PagedResponse<Company> company);
}
