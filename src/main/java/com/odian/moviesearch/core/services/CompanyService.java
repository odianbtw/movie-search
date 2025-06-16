package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.utils.Pageable;

public interface CompanyService {
    Company create (Company company);
    Company findById(Long id);

    Company update(Company company);

    PagedResponse<Company> findAll(Pageable pageable);
}
