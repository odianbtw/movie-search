package com.odian.moviesearch.dao.postgres.mapper.company;


import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.dao.postgres.mapper.CountryEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.MediaEntityMapper;
import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {CountryEntityMapper.class, MediaEntityMapper.class})
public interface CompanyEntityMapper {
    @Mapping(target = "logo", source = "media")
    CompanyEntity to (Company company);
    @InheritInverseConfiguration
    Company to (CompanyEntity company);
    List<Company> to (List<CompanyEntity> companies);
}
