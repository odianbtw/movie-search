package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.ProductionStudioShort;
import com.odian.moviesearch.core.domain.model.ProductionCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductionCompanyDTOMapper {
    ProductionStudioShort domainToShortDto (ProductionCompany company);
}
