package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.CompanyDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.dao.postgres.mapper.CompanyEntityMapper;
import com.odian.moviesearch.dao.postgres.repositories.spring.CompanyRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultCompanyDao implements CompanyDao {

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final CompanyEntityMapper mapper;

    @Override
    public Company create(Company company) {
        var companyEntity = mapper.to(company);
        var country = countryRepository.findById(companyEntity.getCountry().getId())
                .orElseThrow(() -> new NotFoundException("Country with this id not found"));
        companyEntity.setCountry(country);
        return mapper.to(companyRepository.save(companyEntity));
    }
}
