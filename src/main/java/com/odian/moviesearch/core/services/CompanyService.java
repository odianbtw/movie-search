package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.model.Company;

public interface CompanyService {
    Company create (Company company);
    Company findById(Long id);
}
