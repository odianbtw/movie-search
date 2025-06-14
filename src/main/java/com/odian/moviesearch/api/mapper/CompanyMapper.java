package com.odian.moviesearch.api.mapper;


import com.odian.moviesearch.api.model.CompanyDTO;
import com.odian.moviesearch.api.model.CompanyRequest;
import com.odian.moviesearch.core.model.Company;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "ApiCompanyMapper")
public interface CompanyMapper {
    Company to (CompanyRequest company);
    CompanyDTO to (Company company);
}
