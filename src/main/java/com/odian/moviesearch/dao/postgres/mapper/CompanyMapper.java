package com.odian.moviesearch.dao.postgres.mapper;


import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationName = "DaoCompanyMapper")
public interface CompanyMapper {
    CompanyEntity to (Company company);
    Company to (CompanyEntity company);
}
