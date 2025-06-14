package com.odian.moviesearch.dao.postgres.repository.implementations;

import com.odian.moviesearch.core.dao.CompanyDao;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.dao.postgres.mapper.CompanyMapper;
import com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultCompanyDao implements CompanyDao {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    @Override
    public Company create(Company company) {
        return mapper.to(
                repository.save(mapper.to(company))
        );
    }
}
