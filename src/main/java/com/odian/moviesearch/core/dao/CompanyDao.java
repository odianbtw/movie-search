package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.utils.PageableResponse;
import com.odian.moviesearch.core.model.utils.SearchCriteria;

import java.util.Optional;

public interface CompanyDao {
    Company create (Company company);

    Optional<Company> findById(Long id);

    PageableResponse<Company> findAll(SearchCriteria criteria);
}
