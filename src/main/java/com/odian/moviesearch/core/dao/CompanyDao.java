package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Company;

import java.util.Optional;

public interface CompanyDao {
    Company create (Company company);

    Optional<Company> findById(Long id);
}
