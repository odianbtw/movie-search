package com.odian.moviesearch.core.application.service;

import com.odian.moviesearch.core.application.port.in.ProductionCompanyService;
import com.odian.moviesearch.core.application.port.out.ProductionCompanyRepository;
import com.odian.moviesearch.core.domain.model.ProductionCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultProductionCompanyService implements ProductionCompanyService {

    private final ProductionCompanyRepository productionCompanyRepository;

    @Transactional
    @Override
    public ProductionCompany create(ProductionCompany productionCompany) {
        return productionCompanyRepository.create(productionCompany);
    }
}
