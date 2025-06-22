package com.odian.moviesearch.dao.postgres.repositories.implementations;

import com.odian.moviesearch.core.dao.CompanyDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.utils.Pageable;
import com.odian.moviesearch.dao.postgres.mapper.company.CompanyEntityMapper;
import com.odian.moviesearch.dao.postgres.mapper.company.CompanySpecificationMapper;
import com.odian.moviesearch.dao.postgres.mapper.PageableMapper;
import com.odian.moviesearch.dao.postgres.repositories.spring.CompanyRepository;
import com.odian.moviesearch.dao.postgres.repositories.spring.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultCompanyDao implements CompanyDao {

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final CompanyEntityMapper mapper;
    private final PageableMapper pageableMapper;
    private final CompanySpecificationMapper specificationMapper;

    @Override
    public Company create(Company company) {
        var companyEntity = mapper.to(company);
        var country = countryRepository.findById(companyEntity.getCountry().getId())
                .orElseThrow(() -> new NotFoundException("Country with this id not found"));
        companyEntity.setCountry(country);
        return mapper.to(companyRepository.save(companyEntity));
    }

    @Override
    public Optional<Company> findById(Long id) {
        var companyEntity = companyRepository.findById(id).orElse(null);
        return Optional.ofNullable(mapper.to(companyEntity));
    }

    @Override
    public PagedResponse<Company> findAll(Pageable pageable) {
        var page = pageableMapper.to(pageable);
        var specification = specificationMapper.to(pageable.getParameters());
        var result = companyRepository.findAll(specification, page);
        return new PagedResponse<Company>(
                result.getTotalElements(),
                result.getTotalPages(),
                pageable.getCurrentPage(),
                pageable.getPageSize(),
                result.get().map(mapper::to).toList()
        );
    }

    @Override
    public Company update(Company company) {
        var entity = mapper.to(company);
        var companyEntity = companyRepository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("The company with this this id not found"));
        var country = countryRepository.findById(entity.getCountry().getId())
                .orElseThrow(() -> new NotFoundException("The country with this this id not found"));
        companyEntity.setCountry(country);
        companyEntity.setLogo(entity.getLogo());
        companyEntity.setName(entity.getName());
        companyEntity.setDescription(entity.getDescription());
        return mapper.to(companyEntity);
    }
}
