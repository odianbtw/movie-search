package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.CompanyDTO;
import com.odian.moviesearch.api.model.CompanyItemDTO;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.api.model.PagedResponseDTO;
import com.odian.moviesearch.core.model.Company;

import com.odian.moviesearch.core.model.PagedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = CountryDTOMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyDTOMapper {
    @Mapping(target = "country.id", source = "countryId")
    @Mapping(target = "media.url", source = "logoUrl")
    Company to (CompanyRequest company);
    CompanyDTO to (Company company);
    Company to (CompanyDTO companyDTO);
    CompanyItemDTO map (Company company);
    PagedResponseDTO<CompanyItemDTO> to (PagedResponse<Company> company);

}
