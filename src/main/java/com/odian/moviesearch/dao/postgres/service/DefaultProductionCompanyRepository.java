package com.odian.moviesearch.dao.postgres.service;

import com.odian.moviesearch.core.application.port.out.ProductionCompanyRepository;
import com.odian.moviesearch.core.domain.model.ProductionCompany;
import com.odian.moviesearch.dao.postgres.mapper.ProductionCompanyEntityMapper;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataProductionCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultProductionCompanyRepository implements ProductionCompanyRepository {

    private final ProductionCompanyEntityMapper companyMapper;
    private final SpringDataProductionCompanyRepository companyRepository;


    @Override
    public ProductionCompany create(ProductionCompany productionCompany) {
        var entity = companyMapper.domainToEntity(productionCompany);
        return companyMapper.entityToDomain(companyRepository.save(entity));
    }
}
